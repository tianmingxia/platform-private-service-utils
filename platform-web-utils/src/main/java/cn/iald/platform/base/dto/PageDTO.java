package cn.iald.platform.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 基础分页DTO
 *
 * @author wangyc
 * @date 2021-06-05 14:53:00
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO implements Serializable {
	/**
	 * 行数
	 */
	private Integer pageSize;

	/**
	 * 页数
	 */
	private Integer pageNum;

	/**
	 * 是否启用分页 0-禁用 1 启用
	 */
	private Integer pageFlag;
}
