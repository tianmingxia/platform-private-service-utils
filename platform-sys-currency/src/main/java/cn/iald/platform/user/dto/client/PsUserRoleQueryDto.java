package cn.iald.platform.user.dto.client;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户-角色表QueryDto类
 *
 * @author wangyc
 * @version 2020年10月21日 15:02:58 初始创建
 */
@Data
public class PsUserRoleQueryDto {

	/**
	 * 用户id
	 */
	@NotNull(message = "用户主键不能为空")
	private Long userId;

	/**
	 * 角色所属系统id
	 */
	private Integer systemId;

	/**
	 * 用户id列表
	 */
	private List<Long> userIdList;

}