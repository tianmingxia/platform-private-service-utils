package cn.iald.platform.role.dto.client;

import cn.iald.platform.dto.PageDto;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 角色表QueryDto类
 *
 * @author wangyc
 * @version 2020年10月18日 17:01:23 初始创建
 */
@Data
public class PsRoleQueryDto extends PageDto {

	/**
	 * 角色编码
	 */
	private String roleCode;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 职能描述
	 */
	private String roleDesc;

	/**
	 * 所属系统id，对应字典表
	 */
	private Integer systemId;

	/**
	 * 是否默认角色1是 0否，一个系统只能有一个默认角色
	 */
	private Integer defFlag;

	/**
	 * 状态（1有效 -1禁用 0删除）
	 */
	private Integer status;

}