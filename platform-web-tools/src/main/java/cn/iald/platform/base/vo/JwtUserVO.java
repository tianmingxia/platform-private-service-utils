package cn.iald.platform.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Jwt用户Vo
 *
 * @author wangyc
 * @date 2021-06-05 14:53:00
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtUserVO implements Serializable {
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
     * 昵称
     */
    private String nickname;

    /**
     * 登录Ip
     */
    private String loginIp;

    /**
     * 当前用户指定系统角色id
     */
    private List<Long> roleIds;
}
