package cn.iald.platform.utils;

import java.sql.Timestamp;

/**
 * @description: 时间处理工具类
 * @author: wangyc
 * @create: 2020-10-17 15:52
 **/
public class TimeUtils {

	/**
	 * 获取当前时间
	 *
	 * @return
	 */
	public static Timestamp getCurrentTime() {
		return new Timestamp(System.currentTimeMillis());
	}
}
