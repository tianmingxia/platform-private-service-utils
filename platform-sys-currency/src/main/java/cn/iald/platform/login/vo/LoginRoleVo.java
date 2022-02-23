package cn.iald.platform.login.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description: 登录用户角色信息
 * @author: wangyc
 * @create: 2020-11-05 15:08
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRoleVo implements Serializable {
	/**
	 * 主键
	 */
	private Long userRoleId;

	/**
	 * 角色id
	 */
	private Long roleId;

	/**
	 * 赋权时间
	 */
	private Timestamp createTime;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 职能描述
	 */
	private String roleDesc;
}
