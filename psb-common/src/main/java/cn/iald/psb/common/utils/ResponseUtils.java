package cn.iald.psb.common.utils;

import cn.hutool.core.util.StrUtil;
import cn.iald.psb.common.vo.ResponseVo;

/**
 * 返回请求工具类
 *
 * @author wangyc
 */
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
	 * 正常返回数据
	 *
	 * @param data
	 * @return
	 */
	public static ResponseVo success(Object data) {
		if (data == null) {
			data = "";
		}
		return new ResponseVo(SUCCESS_VAL, SUCCESS, data, "");
	}

	/**
	 * 异常返回数据
	 *
	 * @param errorMsg
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
	 * @param code
	 * @param errorMsg
	 * @return
	 */
	public static ResponseVo error(String code, String errorMsg) {
		if (StrUtil.isBlank(errorMsg)) {
			errorMsg = "";
		}
		return new ResponseVo(code, ERROR, "", errorMsg);
	}
}
