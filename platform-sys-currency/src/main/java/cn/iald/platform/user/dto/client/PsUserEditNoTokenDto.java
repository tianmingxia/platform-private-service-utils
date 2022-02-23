package cn.iald.platform.user.dto.client;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 用户基础信息表EditDto类
 *
 * @author wangyc
 * @version 2020年10月18日 17:01:23 初始创建
 */
@Data
public class PsUserEditNoTokenDto {

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
	 * 状态（1有效 -1禁止登录 0删除 ）
	 */
	private Integer status;

	/**
	 * 所属系统id，对应字典表
	 */
	private Integer systemId;

	/**
	 * 系统默认角色id
	 */
	private Long defRoleId;

	/**
	 * 修改人
	 */
	private Long updateBy;
}