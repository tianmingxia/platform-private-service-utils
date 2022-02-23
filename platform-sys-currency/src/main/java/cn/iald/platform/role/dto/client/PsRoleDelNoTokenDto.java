package cn.iald.platform.role.dto.client;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;

/**
 * 角色表EditDto类
 *
 * @author wangyc
 * @version 2020年10月18日 17:01:23 初始创建
 */
@Data
public class PsRoleDelNoTokenDto {

	/**
	 * 主键
	 */
	@NotNull(message = "角色id不能为空")
	private Long roleId;

	/**
	 * 修改人
	 */
	private Long updateBy;

	/**
	 * 所属系统id
	 */
	private Integer systemId;
}