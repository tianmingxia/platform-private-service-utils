package cn.iald.platform.utils;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.codec.Base64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @description: AES工具类
 * @author: wangyc
 * @create: 2020-12-17 16:46
 **/
public class AesUtil {

	/**
	 * AES加密
	 *
	 * @param data    明文
	 * @param key     密钥，长度16
	 * @param padding 算法/模式/补码方式
	 * @return 密文
	 * @throws Exception 加密过程中的异常信息
	 */
	public static String encrypt(String data, String key, String padding) throws Exception {
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance(padding);
		IvParameterSpec zeroIv = new IvParameterSpec(new byte[cipher.getBlockSize()]);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, zeroIv);
		byte[] byteContent = data.getBytes(StandardCharsets.UTF_8);
		byte[] encrypted = cipher.doFinal(byteContent);
		return Base64Encoder.encode(encrypted);
	}

	/**
	 * AES 解密
	 *
	 * @param data    密文
	 * @param key     密钥，长度16
	 * @param padding 算法/模式/补码方式
	 * @return 明文
	 * @throws Exception 加密过程中的异常信息
	 */
	public static String decrypt(String data, String key, String padding) throws Exception {
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance(padding);
		IvParameterSpec zeroIv = new IvParameterSpec(new byte[cipher.getBlockSize()]);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, zeroIv);
		byte[] byteContent = Base64Decoder.decode(data);
		byte[] decrypted = cipher.doFinal(byteContent);
		return new String(decrypted, StandardCharsets.UTF_8);
	}

}
