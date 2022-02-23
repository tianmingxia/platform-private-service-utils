package cn.iald.platform.menu.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 菜单信息表EditDto类
 *
 * @author wangyc
 * @version 2020年12月10日 17:53:47 初始创建
 */
@Data
public class SysMenuInfoEditDto implements Serializable {

	/**
	 * 主键
	 */
	@NotNull(message = "菜单主键不能为空")
	private Long menuId;

	/**
	 * 菜单编码，具有唯一性，同一系列权限的菜单使用同一编码，方便权限控制
	 */
	@NotEmpty(message = "菜单编码不能为空")
	@Length(max = 50, message = "菜单编码不能超过50个字符")
	private String menuCode;

	/**
	 * 菜单名称
	 */
	@NotEmpty(message = "菜单名称不能为空")
	@Length(max = 150, message = "菜单名称不能超过150个字符")
	private String menuName;

	/**
	 * 跳转地址
	 */
	@Length(max = 500, message = "跳转地址不能超过500个字符")
	private String menuUrl;

	/**
	 * 地址扩展字段
	 */
	@Length(max = 255, message = "地址扩展字段不能超过255个字符")
	private String menuUrlEx;

	/**
	 * 类型（1模块 2菜单 3接口 4按钮）
	 */
	@NotNull(message = "菜单类型不能为空")
	private Integer menuType;

	/**
	 * 上级菜单
	 */
	@NotNull(message = "上级菜单不能为空")
	private Long parentId;

	/**
	 * 显示顺序
	 */
	@NotNull(message = "菜单显示顺序不能为空")
	private Integer sortNo;

	/**
	 * 菜单图标
	 */
	private String menuIco;

	/**
	 * 状态（1有效 -1禁用）
	 */
	private Integer status;

	/**
	 * 修改人
	 */
	private Long updateBy;

	/**
	 * 所属平台id，对应字典表
	 */
	private Integer platformId;

	/**
	 * 所属系统id，对应字典表
	 */
	private Integer systemId;
}
