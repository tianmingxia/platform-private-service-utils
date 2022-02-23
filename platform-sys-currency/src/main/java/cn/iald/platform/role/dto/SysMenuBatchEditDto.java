package cn.iald.platform.role.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
 * 菜单表BatchEditDto类
 *
 * @author wangyc
 * @version 2020年10月18日 17:01:23 初始创建
 */
@Data
public class SysMenuBatchEditDto {

	/**
	 * 主键
	 */
	@NotEmpty(message = "菜单主键列表不能为空")
	private List<Long> menuIdList;

	/**
	 * 状态（1有效 -1禁用 0删除）
	 */
	@NotNull(message = "更新状态不能为空")
	private Integer status;

	/**
	 * 修改人
	 */
	private Long updateBy;

	/**
	 * 修改时间
	 */
	private Timestamp updateTime;
}