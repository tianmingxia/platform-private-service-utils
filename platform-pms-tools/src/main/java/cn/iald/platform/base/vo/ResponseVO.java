package cn.iald.platform.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回请求vo
 *
 * @author wangyc
 * @date 2021-06-05 14:53:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO implements Serializable {
	/**
	 * 返回值编码
	 */
	private String code;

	/**
	 * 返回值状态
	 */
	private String status;

	/**
	 * 返回值数据
	 */
	private Object data;

	/**
	 * 返回提示语
	 */
	private String errorMsg;

	/**
	 * 异常信息
	 */
	private String exceptionMsg;
}
