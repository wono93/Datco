package common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class Utils {

	/**
	 * 암호화 : 단방향 암호화
	 * 
	 * 암호화 알고리즘 (md5, sha1, sha256, sha512)
	 * 1. 암호화 byte[] 
	 * 2. 인코딩(Base64)
	 */
	public static String getSha512(String rawPassword) {
		String encryptedPassword = null;
		
		//1.암호화
		MessageDigest md = null;
		byte[] bytes = null;
		byte[] encryptedBytes = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
			bytes = rawPassword.getBytes();
			md.update(bytes);//md객체에 바이트배열 갱신
			encryptedBytes = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println("encryptedBytes="+new String(encryptedBytes));
		
		//2.encoding
		Encoder encoder = Base64.getEncoder();
		encryptedPassword = encoder.encodeToString(encryptedBytes);
		return encryptedPassword;
	}
}






