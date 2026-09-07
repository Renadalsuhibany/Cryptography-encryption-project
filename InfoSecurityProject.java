package InfoSecurityProject01;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Scanner;

public class InfoSecurityProject {
    
    public static int getShiftKey(String key) {
        int shiftKey = key.charAt(0) - 65;
        return shiftKey;
    }

    private static byte rotateLeft(byte b, int shift) {
        int s = shift & 0x07;
        int v = (b & 0xFF);
        int res = ((v << s) | (v >>> (8 - s))) & 0xFF;
        return (byte) res;
    }

    // ENCRYPTION 
    public static byte[] encrypt(byte[] data, byte[] key, int rotateShift) {
        byte[] out = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            byte d = data[i];
            byte r = rotateLeft(d, rotateShift);  //first algorithm=Rotation left
            byte k = key[i % key.length];         
            out[i] = (byte) (r ^ k); //Second algorithm=XOR
        }
        return out;
    }

    //  SHA-256 
    public static String sha256Hex(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(data);
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("SHA-256 Error", e);
        }
    }

    // --------------------------- HASHING THE CIPHERTEXT ----------------------
    public static String generateCipherHash(byte[] cipherBytes) {
        return sha256Hex(cipherBytes);
    }

    // --------------------------- VERIFICATION --------------------------------
    public static boolean verifyCipherHash(String originalHash, byte[] cipherBytes) {
        String newHash = generateCipherHash(cipherBytes);
        return newHash.equals(originalHash);
    }

    private static void printOptions() {
        System.out.println("------------------------------");
        System.out.println("1 - Encrypt Text");
        System.out.println("2 - Exit");
        System.out.println("------------------------------");
        System.out.print("Select an option: ");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String userChoice;

        while (true) {
            printOptions();
            userChoice = input.nextLine().trim();

            if (userChoice.equals("2")) {
                System.out.println("Program will now exit.");
                break;
            }

            switch (userChoice) {
                case "1": {
                    System.out.print("Enter the plaintext: ");
                    String plaintext = input.nextLine().toUpperCase();

                    System.out.print("Enter key: ");
                    String keyStr = input.nextLine().toUpperCase();

                    int shiftKey = getShiftKey(keyStr);

                    byte[] plainBytes = plaintext.getBytes(StandardCharsets.UTF_8);
                    byte[] keyBytes = keyStr.getBytes(StandardCharsets.UTF_8);

                    // ENCRYPT
                    byte[] cipherBytes = encrypt(plainBytes, keyBytes, shiftKey);
                    String cipherB64 = Base64.getEncoder().encodeToString(cipherBytes);

                    System.out.println("\nCiphertext (Base64): " + cipherB64);

                    // --------------------- HASH ON CIPHERTEXT ---------------------
                    String cipherHash = generateCipherHash(cipherBytes);
                    System.out.println("SHA-256 Hash of Ciphertext: " + cipherHash);

                    // --------------------- VERIFY INTEGRITY ------------------------
                    boolean verified = verifyCipherHash(cipherHash, cipherBytes);
                    System.out.println("Ciphertext Integrity Verified? " + verified);

                    System.out.println("\n------------------------------");
                    break;
                }

                default:
                    System.out.println("Invalid option! Please try again.\n");
                    break;
            }
        }
        input.close();
    }
}