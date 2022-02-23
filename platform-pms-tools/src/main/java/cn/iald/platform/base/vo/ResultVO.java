package cn.iald.platform.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回请求分页data内部Vo
 *
 * @author wangyc
 * @date 2021-06-05 14:53:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO implements Serializable {

	/**
	 * 总行数
	 */
	private Integer count;

	/**
	 * 对应数据
	 */
	private Object list;
}
