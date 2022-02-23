package cn.iald.platform.login.vo;

import cn.hutool.core.lang.tree.Tree;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @description: 登录用户VO
 * @author: wangyc
 * @create: 2020-11-05 14:47
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginUserNoTokenVo implements Serializable {
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
	 * 密码（加密）
	 */
	private String password;

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
	 * 创建人
	 */
	private Long createBy;

	/**
	 * 创建时间
	 */
	private Timestamp createTime;

	/**
	 * 修改人
	 */
	private Long updateBy;

	/**
	 * 修改时间
	 */
	private Timestamp updateTime;

	/**
	 * 当前用户菜单权限
	 */
	private List<String> menuCodes;

	/**
	 * 所属系统权限菜单
	 */
	private List<Tree<String>> menuTreeList;

	/**
	 * 当前用户指定系统角色信息
	 */
	private List<LoginRoleVo> roleList;

	/**
	 * 当前用户指定系统角色id
	 */
	private List<Long> roleIds;

	/**
	 * jwt辅助id
	 */
	private String jwtId;

	/**
	 * token
	 */
	private String token;

}
