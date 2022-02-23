package cn.iald.platform.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回请求vo
 *
 * @author wangyc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVo {
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
}
