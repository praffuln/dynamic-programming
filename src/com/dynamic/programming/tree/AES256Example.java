package com.dynamic.programming.tree;

import javax.crypto.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class AES256Example {

	public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
			BadPaddingException, IllegalBlockSizeException {
		Scanner scanner = new Scanner(System.in);

		// Get the encryption key from the user
		System.out.println("Enter your encryption key:");
		String encryptionKey = "prafful.namdev@se.com";

		String originalMessage = " I want to decrypt";

		// Encrypt the message
		byte[] encryptedMessage = encrypt(originalMessage, encryptionKey);

		// Decrypt the message
		String decryptedMessage = decrypt(encryptedMessage, encryptionKey);

		System.out.println("Original message: " + originalMessage);
		System.out.println("Encrypted message: " + Base64.getEncoder().encodeToString(encryptedMessage));
		System.out.println("Decrypted message: " + decryptedMessage);

		scanner.close();
	}

	public static byte[] encrypt(String message, String encryptionKey) throws NoSuchPaddingException,
			NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		// Truncate or pad the key to 128, 192, or 256 bits
		encryptionKey = adjustKeyLength(encryptionKey);

		SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		return cipher.doFinal(message.getBytes());
	}

	public static String decrypt(byte[] encryptedMessage, String encryptionKey) throws NoSuchPaddingException,
			NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		// Truncate or pad the key to 128, 192, or 256 bits
		encryptionKey = adjustKeyLength(encryptionKey);

		SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		byte[] decryptedBytes = cipher.doFinal(encryptedMessage);
		return new String(decryptedBytes);
	}

	public static String adjustKeyLength(String key) {
		int keyLength = key.length();
		if (keyLength < 16) { // Pad with zeros if key length is less than 16
			return String.format("%-16s", key).replace(' ', '0');
		} else if (keyLength < 24) { // Pad with zeros if key length is less
										// than 24
			return String.format("%-24s", key).replace(' ', '0');
		} else if (keyLength < 32) { // Pad with zeros if key length is less
										// than 32
			return String.format("%-32s", key).replace(' ', '0');
		} else if (keyLength > 32) { // Truncate if key length is greater than
										// 32
			return key.substring(0, 32);
		}
		return key;
	}
}
