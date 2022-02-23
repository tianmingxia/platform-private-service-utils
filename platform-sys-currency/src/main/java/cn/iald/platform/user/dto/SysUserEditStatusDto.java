package cn.iald.platform.user.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description: 启用/禁用用户
 * @author: wangyc
 * @create: 2020-12-15 15:15
 **/
@Data
public class SysUserEditStatusDto {
	/**
	 * 用户id
	 */
	@NotNull(message = "用户主键不能为空")
	private Long userId;

	/**
	 * 状态（1有效 -1禁用）
	 */
	@NotNull(message = "启用/禁用不能为空")
	private Integer status;

	/**
	 * 修改人
	 */
	private Long updateBy;
}
