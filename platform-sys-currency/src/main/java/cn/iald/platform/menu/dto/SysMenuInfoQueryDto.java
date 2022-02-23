package cn.iald.platform.menu.dto;

import cn.iald.platform.dto.PageDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 菜单信息表QueryDto类
 *
 * @author wangyc
 * @version 2020年12月10日 17:53:47 初始创建
 */
@Data
public class SysMenuInfoQueryDto extends PageDto {

	/**
	 * 获取数据粒度 1-指定类型的数据本身   2-指定类型的数据及其直属子节点 3-全部
	 */
	@NotEmpty(message = "获取数据粒度不能为空")
	private String childFlag;

	/**
	 * 主键
	 */
	private Long menuId;

	/**
	 * 菜单编码，具有唯一性，同一系列权限的菜单使用同一编码，方便权限控制
	 */
	private String menuCode;

	/**
	 * 菜单名称
	 */
	private String menuName;

	/**
	 * 类型（1模块 2菜单 3接口 4按钮）
	 */
	private Integer menuType;

	/**
	 * 上级菜单
	 */
	private Long parentId;

	/**
	 * 状态（1有效 -1禁用）
	 */
	private Integer status;

	/**
	 * 所属平台id，对应字典表
	 */
	private Integer platformId;

	/**
	 * 所属系统id，对应字典表
	 */
	private Integer systemId;

}
