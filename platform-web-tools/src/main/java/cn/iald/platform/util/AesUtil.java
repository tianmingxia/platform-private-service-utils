package cn.iald.platform.util;

import cn.iald.platform.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * Aes加密工具类
 *
 * @author admin
 * @date 2020-11-13 16:35:00
 **/
@Slf4j
public class AesUtil {
    /**
     * 默认密钥
     */
    private static final String KEY = "d9ead8dfd9ead8df";

    /**
     * 默认偏移量
     */
    private static final String IV = "1234567812345678";

    /**
     * 默认补码方式
     */
    private static final String NO_PADDING = "AES/CBC/NoPadding";


    /**
     * 明文加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encrypt(String data) {
        return encrypt(NO_PADDING, data, KEY, IV);
    }

    /**
     * 密文解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String decrypt(String data) {
        return decrypt(NO_PADDING, data, KEY, IV);
    }

    /**
     * 加密
     *
     * @param padding 补码方式
     * @param data    明文
     * @param key     密钥
     * @param iv      偏移量
     * @return 密文
     */
    public static String encrypt(String padding, String data, String key, String iv) {
        try {
            Cipher cipher = Cipher.getInstance(padding);
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return encode(encrypted).trim();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException(e.getMessage());
        }
    }


    /**
     * 解密
     *
     * @param padding 补码方式
     * @param data    密文
     * @param key     密钥
     * @param iv      偏移量
     * @return 明文
     */
    public static String decrypt(String padding, String data, String key, String iv) {
        try {
            byte[] encrypted = decode(data);
            Cipher cipher = Cipher.getInstance(padding);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] original = cipher.doFinal(encrypted);
            String originalString = new String(original, StandardCharsets.UTF_8);
            return originalString.trim();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException(e.getMessage());
        }
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
