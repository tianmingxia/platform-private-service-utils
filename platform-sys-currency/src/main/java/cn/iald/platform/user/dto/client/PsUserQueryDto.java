package cn.iald.platform.user.dto.client;

import cn.iald.platform.dto.PageDto;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 用户基础信息表QueryDto类
 *
 * @author wangyc
 * @version 2020年10月18日 17:01:23 初始创建
 */
@Data
public class PsUserQueryDto extends PageDto {

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
	 * 角色id
	 */
	private Long roleId;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 精准查询真实姓名
	 */
	private String eqRealName;

	/**
	 * 精准查询登录名
	 */
	private String eqUsername;

	/**
	 * 系统id
	 */
	private Integer systemId;

}