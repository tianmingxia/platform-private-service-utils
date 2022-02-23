package cn.iald.platform.role.dto.client;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 角色表EditDto类
 *
 * @author wangyc
 * @version 2020年10月18日 17:01:23 初始创建
 */
@Data
public class PsRoleEditNoTokenDto {

	/**
	 * 主键
	 */
	@NotNull(message = "角色id不能为空")
	private Long roleId;

	/**
	 * 角色编码
	 */
	@NotEmpty(message = "角色编码不能为空")
	@Length(max = 50, message = "角色编码不能超过50个字符")
	private String roleCode;

	/**
	 * 角色名称
	 */
	@NotEmpty(message = "角色名称不能为空")
	@Length(max = 150, message = "角色名称不能超过150个字符")
	private String roleName;

	/**
	 * 职能描述
	 */
	@Length(max = 350, message = "职能描述不能超过350个字符")
	private String roleDesc;

	/**
	 * 所属平台id，对应字典表
	 */
	private Integer platformId;

	/**
	 * 所属系统id，对应字典表
	 */
	private Integer systemId;

	/**
	 * 是否默认角色1是 0否，一个系统只能有一个默认角色
	 */
	@NotNull(message = "是否默认角色不能为空")
	private Integer defFlag;

	/**
	 * 状态（1有效 -1禁用 0删除）
	 */
	private Integer status;

	/**
	 * 显示顺序
	 */
	@NotNull(message = "角色显示顺序不能为空")
	private Integer sortNo;

	/**
	 * 修改人
	 */
	private Long updateBy;
}