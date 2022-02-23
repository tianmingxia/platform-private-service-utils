package cn.iald.platform.module.login.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 其他系统登录接口
 * @author: wangyc
 * @create: 2020-10-22 12:54
 **/
@Data
public class LoginKeyDTO implements Serializable {

    private static final long serialVersionUID = 7586661822274973355L;

    /**
     * 访问系统需携带的key
     */
    private String key;

    /**
     * 登录Ip
     */
    private String loginIp;
}
