package cn.iald.platform.menu.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 菜单信息表Vo类
 *
 * @author wangyc
 * @version 2020年12月10日 17:53:47 初始创建
 */
@Data
public class SysMenuInfoVo implements Serializable {

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
	 * 是否有下级菜单（false-否 true-是）
	 */
	private Boolean subFlag;

	/**
	 * 下辖子节点
	 */
	private List<SysMenuInfoVo> children;

}