package cn.iald.platform.user.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户基础信息表Vo类
 *
 * @author wangyc
 * @version 2020年10月18日 17:15:33 初始创建
 */
@Data
public class PsUserVo implements Serializable {

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 真实姓名
	 */
	private String realname;

	/**
	 * 登录名
	 */
	private String username;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 最后一次登录时间
	 */
	private Timestamp lastTime;

	/**
	 * 状态（1有效 -1禁止登录 0删除 9-有效且不允许删除）
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
	 * 创建人
	 */
	private String createName;

	/**
	 * 修改人
	 */
	private String updateName;

	/**
	 * 角色id
	 */
	private String roleId;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 当前系统用户状态（1-启用 -1 禁用 0-未绑定）
	 */
	private Integer sysStatus;
}