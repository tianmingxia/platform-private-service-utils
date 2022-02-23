package cn.iald.platform.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

/**
 * @description: Object对象工具类
 * @author: wangyc
 * @create: 2021-04-14 12:44
 **/
public class ObjUtils {

	/**
	 * 对象是否jsonArray
	 *
	 * @param obj
	 * @return
	 */
	public static boolean isJsonArray(Object obj) {
		if (obj != null && !(JSONUtil.isNull(obj)) && StrUtil.isNotBlank(JSONUtil.toJsonStr(obj))
				&& JSONUtil.isJsonArray(JSONUtil.toJsonStr(obj))) {
			return true;
		}
		return false;
	}

	/**
	 * 对象是否Json
	 *
	 * @param obj
	 * @return
	 */
	public static boolean isJson(Object obj) {
		if (obj != null && !(JSONUtil.isNull(obj)) && StrUtil.isNotBlank(JSONUtil.toJsonStr(obj))
				&& JSONUtil.isJson(JSONUtil.toJsonStr(obj))) {
			return true;
		}
		return false;
	}
}
