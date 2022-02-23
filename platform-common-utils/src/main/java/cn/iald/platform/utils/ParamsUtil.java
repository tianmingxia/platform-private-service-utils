package cn.iald.platform.utils;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.digest.DigestUtil;

import java.sql.Timestamp;
import java.util.Map;

/**
 * @description: 参数工具
 * @author: wangyc
 * @create: 2020-12-17 15:01
 **/
public class ParamsUtil {

	/**
	 * entry之间的连接符
	 */
	public static final String SEPARATOR = "&";

	/**
	 * kv之间的连接符
	 */
	public static final String KEY_VALUE_SEPARATOR = "=";

	/**
	 * 发起请求时间
	 */
	public static final String TIMESTAMP = "timestamp";

	/**
	 * 根据参数排序后拼接为字符串,用于签名
	 * 忽略null的键和值
	 *
	 * @param paramsMap
	 * @return
	 */
	public static String paramsSort(Map<String, Object> paramsMap) {
		paramsMap.put(TIMESTAMP, (System.currentTimeMillis() / 1000));
		return MapUtil.sortJoin(paramsMap, SEPARATOR, KEY_VALUE_SEPARATOR, true);
	}

	/**
	 * 根据参数排序后拼接为字符串,用于签名
	 * 忽略null的键和值
	 *
	 * @param paramsMap
	 * @param timestamp
	 * @return
	 */
	public static String paramsSort(Map<String, Object> paramsMap, Long timestamp) {
		paramsMap.put(TIMESTAMP, timestamp);
		return MapUtil.sortJoin(paramsMap, SEPARATOR, KEY_VALUE_SEPARATOR, true);
	}

	/**
	 * 获取数据的md5哈希值
	 *
	 * @param data
	 * @return
	 */
	public static String paramGetHash16(String data) {
		return DigestUtil.md5Hex16(data);
	}

	/**
	 * 参数排序后返回16位数字签名
	 *
	 * @param paramsMap
	 * @return
	 */
	public static String paramGetHash16(Map<String, Object> paramsMap) {
		String paramStr = paramsSort(paramsMap);
		return DigestUtil.md5Hex16(paramStr);
	}
}
