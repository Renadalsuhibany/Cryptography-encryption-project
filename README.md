# Java Cryptography Project

## Overview

A Java-based cryptography project that implements an encryption and decryption workflow using byte rotation and XOR operations.

The project also incorporates Base64 encoding and SHA-256 hashing to demonstrate ciphertext representation and integrity verification.

## Objectives

* Implement encryption and decryption operations using Java.
* Integrate byte rotation and XOR operations into an encryption workflow.
* Encode ciphertext using Base64.
* Generate SHA-256 hashes for data integrity verification.
* Verify whether ciphertext has been modified or corrupted.

## Technologies

* Java
* Cryptography
* SHA-256
* Base64
* Bitwise XOR
* Byte Rotation

## Encryption Process

1. Convert the plaintext and key into bytes.
2. Calculate the rotation value from the first character of the key.
3. Rotate each plaintext byte to the left.
4. Apply XOR between the rotated data and the key.
5. Encode the resulting ciphertext using Base64.
6. Generate a SHA-256 hash for integrity verification.

## Decryption Process

1. Decode the Base64 ciphertext.
2. Convert the key into bytes.
3. Apply XOR between the ciphertext and the key.
4. Rotate the resulting bytes to the right.
5. Reconstruct the original plaintext.

## Integrity Verification

SHA-256 hashing is used to generate a fingerprint of the ciphertext. The generated hash can be compared with a newly calculated hash to verify whether the ciphertext has remained unchanged.

## Project Structure

```text
java-cryptography-project/
├── README.md
├── src/
│   ├── InfoSecurityProject.java
│   └── Decryption1.java
├── report/
│   └── Cryptography_Project_Report.pdf
└── screenshots/
    ├── encryption-output.png
    ├── decryption-output.png
    └── integrity-verification.png
```

## Documentation

The complete project report contains the project introduction, encryption and decryption methodology, key handling, hashing and integrity verification, block diagrams, flowcharts, output screenshots, references, and source code appendix.

## Academic Project

This project was developed as an academic implementation to demonstrate cryptographic concepts and does not represent a production-grade cryptographic algorithm.
