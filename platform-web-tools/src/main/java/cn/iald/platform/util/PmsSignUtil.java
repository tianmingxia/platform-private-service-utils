package cn.iald.platform.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * 数字签名工具类
 *
 * @author wangyc
 * @date 2021/09/24 15:31:04
 **/
@Slf4j
public class PmsSignUtil {

    /**
     * 默认加密key
     */
    private static final String SIGN_KEY = "d9ead8dfd9ead8df";

    /**
     * 数字签名
     *
     * @param hotelId
     * @param time
     * @param key
     * @return
     */
    public static String createSign(String hotelId, String time, String key) {
        StringBuffer sb = new StringBuffer("hotelId=").append(hotelId)
                .append("&time=").append(time)
                .append("&key=");
        if (StrUtil.isNotEmpty(key)) {
            sb.append(key);
        } else {
            sb.append(SIGN_KEY);
        }
        return encryption(sb.toString()).toUpperCase();
    }

    /**
     * MD5加密
     *
     * @param inStr
     * @return 32位密文
     */
    public static String encryption(String inStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
