package cn.iald.platform.util;

/**
 * 中文处理工具类
 *
 * @author wangyc
 * @date 2021-01-13 15:08:00
 */
public class ChineseUtil {

    /**
     * 对中文进行处理，转为uxxyy这样的unicode表示法
     *
     * @param str
     * @return
     */
    public static String chineseCharacterToUnicode(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            char chr = str.charAt(i);
            if (isChinese(chr)) {
                result += "\\u" + Integer.toHexString(chr);
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }


    /**
     * 判断字符是否中文
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS ||
                ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS ||
                ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A ||
                ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B ||
                ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION ||
                ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS ||
                ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否存在中文
     *
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        for (int i = 0; i < str.length(); i++) {
            char chr = str.charAt(i);
            if (isChinese(chr)) {
                return true;
            }
        }
        return false;
    }
}
