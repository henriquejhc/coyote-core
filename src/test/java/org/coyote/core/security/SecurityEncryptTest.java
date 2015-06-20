package org.coyote.core.security;

public class SecurityEncryptTest {

	public static void main(String[] args) {
		String password = "admin1234";
		System.out.println(SecurityEncrypt.generateEncryptionMD5(password));
		System.out.println(SecurityEncrypt.generateEncryptionSHA256(password));
		
		
		password = "admin1234567890";
		System.out.println(SecurityEncrypt.generateEncryptionMD5(password));
		System.out.println(SecurityEncrypt.generateEncryptionSHA256(password));
	}
	
}