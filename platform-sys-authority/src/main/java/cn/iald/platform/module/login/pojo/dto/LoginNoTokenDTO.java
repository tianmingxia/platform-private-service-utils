package cn.iald.platform.module.login.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 其他系统登录接口
 * @author: wangyc
 * @create: 2020-10-22 12:54
 **/
@Data
public class LoginNoTokenDTO implements Serializable {

    private static final long serialVersionUID = 2705164372356809000L;
    /**
     * 登录名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 要登录系统id
     */
    private Integer systemId;

    /**
     * 登录Ip
     */
    private String loginIp;
}
