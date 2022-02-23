package cn.iald.platform.util;

/**
 * 进制转换工具
 *
 * @author wangyc
 * @date 2020-12-29 21:30
 **/
public class NumericConvertUtil {
    /**
     * 在进制表示中的字符集合，0-Z分别用于表示最大为62进制的符号表示
     */
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private static final int SEED = 62;

    /**
     * 将十进制的数字转换为62进制的字符串
     *
     * @param number 十进制的数字
     * @return 指定进制的字符串
     */
    public static String toOtherNumberStr62(long number) {
        if (number < 0) {
            number = ((long) 2 * 0x7fffffff) + number + 2;
        }
        char[] buf = new char[32];
        int charPos = 32;
        while ((number / SEED) > 0) {
            buf[--charPos] = DIGITS[(int) (number % SEED)];
            number /= SEED;
        }
        buf[--charPos] = DIGITS[(int) (number % SEED)];
        return new String(buf, charPos, (32 - charPos));
    }

    /**
     * 62进制的数字（字符串形式）转换为十进制的数字
     *
     * @param number 其它进制的数字（字符串形式）
     * @return 十进制的数字
     */
    public static long toNumber10(String number) {
        char[] charBuf = number.toCharArray();
        long result = 0, base = 1;

        for (int i = charBuf.length - 1; i >= 0; i--) {
            int index = 0;
            for (int j = 0, length = DIGITS.length; j < length; j++) {
                //找到对应字符的下标，对应的下标才是具体的数值
                if (DIGITS[j] == charBuf[i]) {
                    index = j;
                }
            }
            result += index * base;
            base *= SEED;
        }
        return result;
    }
}
