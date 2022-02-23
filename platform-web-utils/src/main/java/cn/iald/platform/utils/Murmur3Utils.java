package cn.iald.platform.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * @description: murmur3工具类
 * @author: wangyc
 * @create: 2020-12-29 15:24
 **/
public class Murmur3Utils {
	/**
	 * 获取32位hash码
	 *
	 * @param str
	 * @return
	 */
	public static String murmur32(String str) {
		return Hashing.murmur3_32().hashString(str, StandardCharsets.UTF_8).toString();
	}

	/**
	 * 获取128位hash码
	 *
	 * @param str
	 * @return
	 */
	public static String murmur128(String str) {
		return Hashing.murmur3_128().hashString(str, StandardCharsets.UTF_8).toString();
	}
}
