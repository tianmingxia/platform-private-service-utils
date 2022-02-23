package cn.iald.platform.util;

import cn.hutool.core.util.CharsetUtil;
import cn.iald.platform.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA算法工具
 *
 * @author wangyc
 * @date 2021/07/13 17:57:42
 **/
@Slf4j
public class ShaUtil {

    /**
     * 默认加密算法方式
     */
    private static final String ALGORITHM = "SHA-256";

    /**
     * 加密
     *
     * @param data 要加密的明文
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encrypt(String data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            byte[] hash = messageDigest.digest(data.getBytes(CharsetUtil.CHARSET_UTF_8));
            return Hex.encodeHexString(hash);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * 加密
     *
     * @param data      要加密的明文
     * @param algorithm 加密算法方式
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encrypt(String data, String algorithm) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] hash = messageDigest.digest(data.getBytes(CharsetUtil.CHARSET_UTF_8));
            return Hex.encodeHexString(hash);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException(e.getMessage());
        }
    }
}
