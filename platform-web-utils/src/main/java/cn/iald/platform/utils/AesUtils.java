package cn.iald.platform.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @description: Aes加密工具类
 * @author: admin
 * @create: 2020-11-13 16:35
 **/
public class AesUtils {

	public static final String KEY = "d9ead8dfd9ead8df";

	public static final String IV = "1234567812345678";


	/**
	 * 明文加密
	 *
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data) throws Exception {
		return encryptAes(data, KEY, IV);
	}

	/**
	 * 密文解密
	 *
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String data) throws Exception {
		return decryptAes(data, KEY, IV);
	}

	/**
	 * 加密
	 *
	 * @param data 明文
	 * @param key  密钥，长度16
	 * @param iv   偏移量，长度16
	 * @return 密文
	 */
	public static String encryptAes(String data, String key, String iv) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		int blockSize = cipher.getBlockSize();
		byte[] dataBytes = data.getBytes();
		int plaintextLength = dataBytes.length;
		if (plaintextLength % blockSize != 0) {
			plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
		}
		byte[] plaintext = new byte[plaintextLength];
		System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
		SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
		IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
		byte[] encrypted = cipher.doFinal(plaintext);
		return encode(encrypted).trim();
	}


	/**
	 * 解密
	 *
	 * @param data 密文
	 * @param key  密钥，长度16
	 * @param iv   偏移量，长度16
	 * @return 明文
	 */
	public static String decryptAes(String data, String key, String iv) throws Exception {
		byte[] encrypted = decode(data);
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
		byte[] original = cipher.doFinal(encrypted);
		String originalString = new String(original, StandardCharsets.UTF_8);
		return originalString.trim();
	}

	/**
	 * 编码
	 *
	 * @param byteArray
	 * @return
	 */
	public static String encode(byte[] byteArray) {
		return new String(new Base64().encode(byteArray));
	}

	/**
	 * 解码
	 *
	 * @param base64EncodedString
	 * @return
	 */
	public static byte[] decode(String base64EncodedString) {
		return new Base64().decode(base64EncodedString);
	}
}
