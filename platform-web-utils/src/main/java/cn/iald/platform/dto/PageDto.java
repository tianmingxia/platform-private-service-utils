package cn.iald.platform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页dto类
 *
 * @author wangyc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {
	/**
	 * 行数
	 */
	private Integer pageSize = 10;

	/**
	 * 页数
	 */
	private Integer pageNum = 1;

	/**
	 * 是否启用分页 0-禁用 1 启用
	 */
	private Integer pageFlag = 1;

	/**
	 * 不分页是否获取总行数 0-禁用 1 启用
	 */
	private Integer countFlag = 0;
}
