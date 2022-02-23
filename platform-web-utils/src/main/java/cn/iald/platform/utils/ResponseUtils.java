package cn.iald.platform.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.iald.platform.vo.ResponseVo;
import cn.iald.platform.vo.ResultVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回请求工具类
 * 2021-06-24废弃，替换为：cn.iald.platform.utils.ResponseUtil
 * @author wangyc
 */
@Deprecated
public class ResponseUtils {

	/**
	 * 请求成功响应的状态
	 */
	public static final String SUCCESS = "success";

	/**
	 * 请求成功响应的编码
	 */
	public static final String SUCCESS_VAL = "0";

	/**
	 * 请求异常响应的状态
	 */
	public static final String ERROR = "error";

	/**
	 * 请求默认异常响应的编码
	 */
	public static final String ERROR_VAL = "-1";

	/**
	 * APP返回值key
	 */
	public static final String APP_VAL_KEY = "obj";

	/**
	 * 请求成功，正常返回数据
	 *
	 * @param data 要返回的数据
	 * @return
	 */
	public static ResponseVo success(Object data) {
		if (data == null) {
			data = "";
		}
		return new ResponseVo(SUCCESS_VAL, SUCCESS, data, "");
	}

	/**
	 * 请求成功，APP正常返回数据
	 *
	 * @param data 要返回的数据，对应key[obj]
	 * @return
	 */
	public static ResponseVo successApp(Object data) {
		if (data == null) {
			data = "";
		}
		Map<String, Object> resMap = new HashMap<>(1);
		resMap.put(APP_VAL_KEY, data);
		return new ResponseVo(SUCCESS_VAL, SUCCESS, resMap, "");
	}

	/**
	 * 请求成功，返回list
	 *
	 * @param list 要返回的list
	 * @return list大小及list数据
	 */
	public static ResponseVo successList(List list) {
		if (CollUtil.isNotEmpty(list)) {
			return new ResponseVo(SUCCESS_VAL, SUCCESS, new ResultVo(list.size(), list), "");
		}
		return new ResponseVo(SUCCESS_VAL, SUCCESS, new ResultVo(0, list), "");
	}

	/**
	 * 请求成功，返回list
	 *
	 * @param count 自定义大小
	 * @param list  要返回的list大小
	 * @return
	 */
	public static ResponseVo successList(Long count, Object list) {
		if (count == null || count == 0 || list == null) {
			return new ResponseVo(SUCCESS_VAL, SUCCESS, new ResultVo(0, list), "");
		}
		return new ResponseVo(SUCCESS_VAL, SUCCESS, new ResultVo(count.intValue(), list), "");
	}

	/**
	 * APP端正常分页数据，后期不在采用，已使用的无需变更
	 * 废弃时间：2021-04-30
	 *
	 * @param count
	 * @param list
	 * @return
	 */
	@Deprecated
	public static ResponseVo successApp(Long count, Object list) {
		if (count == null || count == 0 || list == null) {
			return new ResponseVo(SUCCESS_VAL, SUCCESS, new ResultVo(0, list), "");
		}
		return new ResponseVo(SUCCESS_VAL, SUCCESS, new ResultVo(count.intValue(), list), "");
	}

	/**
	 * 正常分页数据，后期不在采用，已使用的无需变更
	 * 废弃时间：2021-04-30
	 *
	 * @param count
	 * @param list
	 * @return
	 */
	@Deprecated
	public static ResponseVo success(Long count, Object list) {
		if (count == null || count == 0 || list == null) {
			return new ResponseVo(SUCCESS_VAL, SUCCESS, "", "");
		}
		return new ResponseVo(SUCCESS_VAL, SUCCESS, new ResultVo(count.intValue(), list), "");
	}

	/**
	 * 异常返回数据
	 *
	 * @param errorMsg 异常描述
	 * @return
	 */
	public static ResponseVo error(String errorMsg) {
		if (StrUtil.isBlank(errorMsg)) {
			errorMsg = "";
		}
		return new ResponseVo(ERROR_VAL, ERROR, "", errorMsg);
	}

	/**
	 * 异常返回数据
	 *
	 * @param code     对应异常码
	 * @param errorMsg 异常描述
	 * @return
	 */
	public static ResponseVo error(String code, String errorMsg) {
		if (StrUtil.isBlank(errorMsg)) {
			errorMsg = "";
		}
		return new ResponseVo(code, ERROR, "", errorMsg);
	}

	/**
	 * 异常返回数据
	 *
	 * @param code     对应异常码
	 * @param errorMsg 异常描述
	 * @param data     要返回的数据
	 * @return
	 */
	public static ResponseVo error(String code, String errorMsg, Object data) {
		if (StrUtil.isBlank(errorMsg)) {
			errorMsg = "";
		}
		return new ResponseVo(code, ERROR, data, errorMsg);
	}

	/**
	 * 异常返回数据列表
	 *
	 * @param code
	 * @param errorMsg
	 * @param list
	 * @return
	 */
	public static ResponseVo errorList(String code, String errorMsg, List list) {
		ResultVo resultVo = new ResultVo(0, list);
		if (CollUtil.isNotEmpty(list)) {
			resultVo.setCount(list.size());
		}
		if (StrUtil.isBlank(errorMsg)) {
			errorMsg = "";
		}
		return new ResponseVo(code, ERROR, resultVo, errorMsg);
	}

	/**
	 * 异常返回数据列表
	 *
	 * @param code
	 * @param errorMsg
	 * @param count
	 * @param list
	 * @return
	 */
	public static ResponseVo errorList(String code, String errorMsg, Long count, List list) {
		ResultVo resultVo = new ResultVo(0, list);
		if (count != null && CollUtil.isNotEmpty(list)) {
			resultVo.setCount(count.intValue());
		}
		return new ResponseVo(code, ERROR, resultVo, errorMsg);
	}


	/**
	 * 请求响应成功，返回值转实体类
	 *
	 * @param responseVo 返回值
	 * @param beanClass  要转换的实体类
	 * @param <T>        返回类型
	 * @return
	 */
	public static <T> T toBean(ResponseVo responseVo, Class<T> beanClass) {
		if (ResponseUtils.SUCCESS_VAL.equals(responseVo.getCode())) {
			Object data = responseVo.getData();
			if (ObjUtils.isJson(data)) {
				return JSONUtil.toBean(JSONUtil.toJsonStr(data), beanClass);
			}
		}
		return null;
	}

	/**
	 * 请求响应成功，返回值转列表
	 *
	 * @param resultVo  分页后列表对象
	 * @param beanClass 要转换的列表对应实体类
	 * @param <T>       返回类型
	 * @return
	 */
	public static <T> List<T> toList(ResultVo resultVo, Class<T> beanClass) {
		Object data = resultVo.getList();
		if (ObjUtils.isJsonArray(data)) {
			return JSONUtil.toList(JSONUtil.parseArray(data), beanClass);
		}
		return null;
	}
}
