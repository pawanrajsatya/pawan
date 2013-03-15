package me;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Blob;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class EncryptDecryptDemo {    
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		char[] hexDigit = {'0', '1', '2', '3', '4', '5', '6', '7','8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		String password = generateHexPassword(hexDigit); System.out.println("Password: "+password);
		//digest the password to form the Key
		MessageDigest md = MessageDigest.getInstance("SHA1");
		System.out.println("Message digest object info:  Algorithm = "+md.getAlgorithm()+" Provider = "+md.getProvider());
		md.update(password.getBytes());
		byte[] bytePassword = md.digest();
		String hexPassword = bytesToHex(bytePassword, hexDigit);
		System.out.println("SHA1(\""+password+"\") =" +hexPassword);

		// Create an array to hold the key
		byte[] hexPasswordByte = hexPassword.getBytes();
		// Create a DESede key spec from the key
		DESedeKeySpec spec = new DESedeKeySpec(hexPasswordByte);
		// Generate a DESede SecretKey object 
		SecretKey theKey = SecretKeyFactory.getInstance("DESede").generateSecret(spec);;

		Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		// setup an IV (initialization vector) that should be
		// randomly generated for each input that's encrypted
		byte[] iv = new byte[cipher.getBlockSize()];
		new SecureRandom().nextBytes(iv);
		IvParameterSpec ivSpec = new IvParameterSpec(iv);

		byte[] encrypt = encrypt(password, cipher, theKey, ivSpec);
		String decrypt = decrypt(cipher, theKey, ivSpec); 
		System.out.println("decrypted Password : "+decrypt);

	}

	public static String bytesToHex(byte[] b, char hexDigit[]) {
		StringBuffer buf = new StringBuffer();
		for (int j=0; j<b.length; j++) {
			buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
			buf.append(hexDigit[b[j] & 0x0f]);
		}
		return buf.toString();
	}

	public static byte[] encrypt(String input, Cipher cipher, SecretKey theKey, IvParameterSpec ivSpec) throws Exception {        
		// encrypt
		cipher.init(Cipher.ENCRYPT_MODE, theKey, ivSpec);
		byte[] inputBytes = input.getBytes("UTF8");
		byte[] encrypted = cipher.doFinal(inputBytes);
		
//		http://stackoverflow.com/questions/10133006/how-to-store-a-java-byte-array-in-an-oracle-database
//				CREATE TABLE pwd_table (id integer primary key, pwd blob);
//				Connection connection = null;
//				PreparedStatement pstmt = connection.prepareStatement(
//				   "insert into pwd_table (id, pwd) values (?, ?)");
//				pstmt.setInt(1, 01);
//		Blob blobValue = new SerialBlob(encrypted);
//		pstmt.setBlob(2, blobValue);
//				pstmt.setBytes(2, encrypted);
//				pstmt.executeUpdate();
//				connection.commit();
		
		// Write the data out to a file
		FileOutputStream out = new FileOutputStream("encrypted.log");
		out.write(encrypted);
		out.close();
		return encrypted;

	}
	
	public static String decrypt(Cipher cipher, SecretKey theKey, IvParameterSpec ivSpec) throws Exception {
		// Decrypt the data
		cipher.init(Cipher.DECRYPT_MODE, theKey, ivSpec);
		File encryptedFile = new File("encrypted.log");
		// Create a byte block to hold the entire encrypted file
		byte[] encryptedText = new byte[(int) encryptedFile.length()];
		FileInputStream fileIn = new FileInputStream(encryptedFile);
		// Read the entire encrypted file
		fileIn.read(encryptedText);
		fileIn.close();
		// Decrypt the data
		byte[] plaintext = cipher.doFinal(encryptedText);
		String plaintextStr = new String(plaintext);
		return plaintextStr;
	}

	//methohd to generate password
	public static String generateHexPassword(char hexDigit[]) {
		//generate a random 8 digit number and allowed strings for password
		String number = String.valueOf((int) (Math.floor(Math.random() * 90000000) + 10000000));
		StringBuffer buf = new StringBuffer();
		//create a 16 digit hex password by traversing the 8 digit number 
		for (int j=0; j<8 ; j++) {
			buf.append(hexDigit[(number.charAt(7-j) + 7) & 0x0f]);
			buf.append(hexDigit[number.charAt(j) & 0x0f]);
		}
		return buf.toString().toLowerCase();
	}
}
