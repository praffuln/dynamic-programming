package com.dynamic.programming.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EncryptionDecryption {

	private static byte[] encryptedBytes;

	// Create a JPanel to hold components for the text field and encryptButton
	private static final JPanel encryptionPanel = new JPanel();
	// Create a plainTextLabel for the text field
	private static final JLabel encryptonlabel = new JLabel("AES Encryption:\n");

	// Create a plainTextLabel for the text field
	private static final JLabel plainTextLabel = new JLabel("PlainText:");

	// Create a text field
	private static final JTextField plainTextField = new JTextField(50);

	// Create a plainTextLabel for the text field
	private static final JLabel encryptionKey = new JLabel("Key:");
	// Create a text field
	private static final JTextField encryptionKeyField = new JTextField(50);

	// encrypted result
	private static final JLabel encryptedResultLbl = new JLabel("Encrypted result:");
	private static final JLabel encryptedResult = new JLabel("");

	// Create a encryptButton
	private static final JButton encryptButton = new JButton("Encrypt");

	// Create a JPanel to hold components for the plainTextLabel
	private static final JPanel decryptionPanel = new JPanel();

	// Create a plainTextLabel for the text field
	private static final JLabel decryptionlabel = new JLabel("AES Decryption:\n");

	// Create a plainTextLabel for the text field
	private static final JLabel cipherTextLabel = new JLabel("cipher Text:");
	// Create a text field
	private static final JTextField cipherTextField = new JTextField(50);

	// Create a plainTextLabel for the text field
	private static final JLabel decryptionKeyLable = new JLabel("Key:");
	// Create a text field
	private static final JTextField decryptionKeyField = new JTextField(50);

	// encrypted result
	private static final JLabel decryptedResultLbl = new JLabel("Decrypted result:");
	private static final JLabel decryptedResult = new JLabel("");

	// Create a encryptButton
	private static final JButton button2 = new JButton("Decrypt");

	public static void main(String[] args) {
		// Create a JFrame
		JFrame frame = createFrame();

		JPanel panel1 = encryptionPanel();
		JPanel panel2 = decryptionPanel();

		// Add panels to the frame
		frame.add(panel1);
		frame.add(panel2);

	}

	private static JFrame createFrame() {
		JFrame frame = new JFrame("AES cryptosystem");

		// Set the size of the frame
		frame.setSize(600, 600);
		// Create a layout to hold both panels
		BoxLayout layout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		frame.setLayout(layout);
		// Set default close operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Make the frame visible
		frame.setVisible(true);
		return frame;
	}

	private static JPanel decryptionPanel() {

		// Add action listener to the encryptButton
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Print the text field value to console
				System.out.println("Text Field Value: " + cipherTextField.getText());

				// Print the text field value to console
				System.out.println("Text Field Value: " + cipherTextField.getText());
				String keyFromUi = decryptionKeyField.getText();
				System.out.println("encryptionKey is: " + keyFromUi);

				try {
					String decryptedMessage = decrypt(encryptedBytes,
							decryptionKeyField.getText());
					decryptedResult.setText(decryptedMessage);
				} catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException
						| IllegalBlockSizeException e1) {
					e1.printStackTrace();
				}

			}
		});

		// Add components to the second panel
		decryptionPanel.add(decryptionlabel);
		decryptionPanel.add(cipherTextLabel);
		decryptionPanel.add(cipherTextField);
		decryptionPanel.add(decryptionKeyLable);
		decryptionPanel.add(decryptionKeyField);
		decryptionPanel.add(decryptedResultLbl);
		decryptionPanel.add(decryptedResult);
		decryptionPanel.add(button2);
		return decryptionPanel;
	}

	private static JPanel encryptionPanel() {

		// Add action listener to the encryptButton
		encryptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Print the text field value to console
				System.out.println("Text Field Value: " + plainTextField.getText());
				String keyFromUi = encryptionKeyField.getText();
				System.out.println("encryptionKey is: " + keyFromUi);

				try {
					byte[] encryptedMessage = encrypt(plainTextField.getText(), encryptionKeyField.getText());
					encryptedBytes = encryptedMessage;
					encryptedResult.setText(Base64.getEncoder().encodeToString(encryptedMessage));
					// set decryption field for user easiness
					cipherTextField.setText(encryptedResult.getText());
					decryptionKeyField.setText(encryptionKeyField.getText());
				} catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException
						| IllegalBlockSizeException e1) {
					e1.printStackTrace();
				}
			}
		});

		// Add components to the first panel
		encryptionPanel.add(encryptonlabel);
		encryptionPanel.add(plainTextLabel);
		encryptionPanel.add(plainTextField);
		encryptionPanel.add(encryptionKey);
		encryptionPanel.add(encryptionKeyField);
		encryptionPanel.add(encryptedResultLbl);
		encryptionPanel.add(encryptedResult);
		encryptionPanel.add(encryptButton);
		return encryptionPanel;
	}

	public static byte[] encrypt(String message, String encryptionKey) throws NoSuchPaddingException,
			NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		// Truncate or pad the encryptionKey to 128, 192, or 256 bits
		encryptionKey = adjustKeyLength(encryptionKey);

		SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		return cipher.doFinal(message.getBytes());
	}

	public static String decrypt(byte[] encryptedMessage, String encryptionKey) throws NoSuchPaddingException,
			NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		// Truncate or pad the encryptionKey to 128, 192, or 256 bits
		encryptionKey = adjustKeyLength(encryptionKey);

		SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		byte[] decryptedBytes = cipher.doFinal(encryptedMessage);
		return new String(decryptedBytes);
	}

	public static String adjustKeyLength(String key) {
		int keyLength = key.length();
		if (keyLength < 16) { // Pad with zeros if encryptionKey length is less
								// than 16
			return String.format("%-16s", key).replace(' ', '0');
		} else if (keyLength < 24) { // Pad with zeros if encryptionKey length
										// is less
										// than 24
			return String.format("%-24s", key).replace(' ', '0');
		} else if (keyLength < 32) { // Pad with zeros if encryptionKey length
										// is less
										// than 32
			return String.format("%-32s", key).replace(' ', '0');
		} else if (keyLength > 32) { // Truncate if encryptionKey length is
										// greater than
										// 32
			return key.substring(0, 32);
		}
		return key;
	}
}
