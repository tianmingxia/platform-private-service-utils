package cn.iald.platform.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回请求分页data内部Vo
 *
 * @author wangyc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo {

	/**
	 * 总行数
	 */
	private Integer count;

	/**
	 * 对应数据
	 */
	private Object list;
}
