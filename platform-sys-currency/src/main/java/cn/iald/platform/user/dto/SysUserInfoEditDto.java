package cn.iald.platform.user.dto;

import java.io.Serializable;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 用户信息表EditDto类
 *
 * @author wangyc
 * @version 2020年12月14日 10:16:58 初始创建
 */
@Data
public class SysUserInfoEditDto implements Serializable {

	/**
	 * 用户id
	 */
	@NotNull(message = "用户主键不能为空")
	private Long userId;

	/**
	 * 登录名
	 */
	@NotEmpty(message = "用户登录名不能为空")
	@Length(max = 50, message = "登录名不能超过50个字符")
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 真实姓名
	 */
	@Length(max = 50, message = "真实姓名不能超过50个字符")
	private String realname;

	/**
	 * 昵称
	 */
	@Length(max = 50, message = "昵称不能超过50个字符")
	private String nickname;

	/**
	 * 所属部门
	 */
	private Long deptId;

	/**
	 * 备注
	 */
	@Length(max = 255, message = "备注不能超过255个字符")
	private String remark;

	/**
	 * 状态（1有效 -1禁用）
	 */
	private Integer status;

	/**
	 * 删除标志( 0- 删除 1-未删除)
	 */
	private Integer delFlag;

	/**
	 * 修改人
	 */
	private Long updateBy;
}