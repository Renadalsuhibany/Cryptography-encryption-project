/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Decryption1;

/**
 *
 * @author dinaa
 */

import java.util.Base64;
import java.util.Scanner;

public class Decryption1 {

    private static byte rotateRight(byte b, int shift) {
        int s = shift & 0x07;
        int v = (b & 0xFF);
        int res = ((v >>> s) | (v << (8 - s))) & 0xFF;
        return (byte) res;
    }

    public static byte[] decrypt(byte[] cipherData, byte[] key, int rotateShift) {
        byte[] out = new byte[cipherData.length];
        for (int i = 0; i < cipherData.length; i++) {
            byte c = cipherData[i];
            byte k = key[i % key.length];
            byte r = (byte) (c ^ k);
            out[i] = rotateRight(r, rotateShift);
        }
        return out;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("--------------------------------");
            System.out.println("1 - Decrypt Text");
            System.out.println("2 - Exit");
            System.out.println("--------------------------------");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter the ciphertext (Base64): ");
                String cipherText = scanner.nextLine();
                System.out.print("Enter the key: ");
                String key = scanner.nextLine().toUpperCase();
                int rotationValue = key.charAt(0) - 65;
                byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
                byte[] keyBytes = key.getBytes();
                byte[] plainBytes = decrypt(cipherBytes, keyBytes, rotationValue);
                String plainText = new String(plainBytes);
                System.out.println("Plaintext: " + plainText.toLowerCase());
            } else if (choice == 2) {
                System.out.println("Program will now exit.");
                break;
            } else {
                System.out.println("Invalid option! Try again.\n");
            }
        }
        scanner.close();
    }
}
