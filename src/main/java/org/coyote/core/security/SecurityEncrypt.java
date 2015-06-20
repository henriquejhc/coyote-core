package org.coyote.core.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public final class SecurityEncrypt {

	
    public static String generateTemporaryPassword(int sizePassword){
        
        char[] digitos = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S',
                                    'T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l',
                                    'm','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4',
                                    '5','6','7','8','9'};
        
        String password = "";
        
        for (int i = 0; i < sizePassword; i++)
            password += digitos[(int) (Math.random() * 63)];
         
        return password;
    }
    
    public static String generateEncryptionMD5(String value) {
    	return generateEncryption(value, "MD5");
    }
    
    public static String generateEncryptionSHA256(String value) {
    	return generateEncryption(value, "SHA-256");
    }
    
	private static String generateEncryption(String value, String algorithm) {															

		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance(algorithm);// MD5 or SHA-256
			BigInteger hash = new BigInteger(1, md.digest(value.getBytes()));
			return hash.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	

	public static String encryption(String password){
		
		PBEKeySpec pbeKeySpec;
		PBEParameterSpec pbeParamSpec;
		SecretKeyFactory keyFac;

		try {
			byte[] salt = { (byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c,
					(byte) 0x7e, (byte) 0xc8, (byte) 0xee, (byte) 0x99 };

			int count = 20;

			pbeParamSpec = new PBEParameterSpec(salt, count);

			pbeKeySpec = new PBEKeySpec(password.toCharArray());
			keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

			Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");

			pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);

			byte[] ciphertext = pbeCipher.doFinal();
			
			return new String(ciphertext);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return null;
	}
	
}