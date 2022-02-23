package cn.iald.platform.menu.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 菜单信息表实体类
 *
 * @author wangyc
 * @version 2020年12月10日 17:53:47 初始创建
 */
@Data
public class SysMenuInfoEntity implements Serializable {

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
	 * 跳转地址
	 */
	private String menuUrl;

	/**
	 * 地址扩展字段
	 */
	private String menuUrlEx;

	/**
	 * 类型（1模块 2菜单 3接口 4按钮）
	 */
	private Integer menuType;

	/**
	 * 上级菜单
	 */
	private Long parentId;

	/**
	 * 显示顺序
	 */
	private Integer sortNo;

	/**
	 * 菜单图标
	 */
	private String menuIco;

	/**
	 * 状态（1有效 -1禁用 ）
	 */
	private Integer status;

	/**
	 * 状态（1未删除 0已删除 ）
	 */
	private Integer delFlag;

	/**
	 * 创建人
	 */
	private Long createBy;

	/**
	 * 创建时间
	 */
	private Timestamp createTime;

	/**
	 * 修改人
	 */
	private Long updateBy;

	/**
	 * 修改时间
	 */
	private Timestamp updateTime;

	/**
	 * 所属平台id，对应字典表
	 */
	private Integer platformId;

	/**
	 * 所属系统id，对应字典表
	 */
	private Integer systemId;

	/**
	 * 是否有下级菜单（0-否 1-是）
	 */
	private Boolean subFlag;

}
