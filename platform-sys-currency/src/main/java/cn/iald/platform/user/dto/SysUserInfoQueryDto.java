package cn.iald.platform.user.dto;

import cn.iald.platform.dto.PageDto;
import lombok.Data;
/**
* 用户信息表QueryDto类
*
* @author wangyc
* @version 2020年12月14日 10:16:58 初始创建
*/
@Data
public class SysUserInfoQueryDto extends PageDto {

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 登录名
    */
    private String username;

    /**
    * 真实姓名
    */
    private String realname;

    /**
    * 昵称
    */
    private String nickname;

    /**
    * 所属部门
    */
    private Long deptId;

    /**
    * 备注
    */
    private String remark;

    /**
    * 状态（1有效 -1禁用）
    */
    private Integer status;

    /**
    * 删除标志( 0- 删除 1-未删除)
    */
    private Integer delFlag;

}