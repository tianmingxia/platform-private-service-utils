package cn.iald.platform.user.dto.client;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 用户基础信息表SaveDto类
 *
 * @author wangyc
 * @version 2020年10月18日 17:01:23 初始创建
 */
@Data
public class PsUserSaveNoTokenDto {

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
	 * 密码
	 */
	private String password;

	/**
	 * 所属系统id，对应字典表
	 */
	private Integer systemId;

	/**
	 * 系统默认角色id
	 */
	private Long defRoleId;

	/**
	 * 状态（1有效 -1禁用0 删除 9-有效且不允许删除）
	 */
	private Integer status;

	/**
	 * 创建人
	 */
	private Long createBy;

	/**
	 * 修改人
	 */
	private Long updateBy;
}