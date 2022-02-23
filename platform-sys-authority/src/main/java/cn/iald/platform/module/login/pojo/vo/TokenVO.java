package cn.iald.platform.module.login.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * TokenVO
 *
 * @author wangyc
 * @date 2021/06/15 14:59:00
 */
@Data
public class TokenVO implements Serializable {

    private static final long serialVersionUID = 1413311350275782013L;

    /**
     * 授权令牌
     */
    private String accessToken;

    /**
     * 权限范围
     */
    private String scope;

    /**
     * 令牌的类型
     */
    private String tokenType;

    /**
     * 访问令牌过期时间
     */
    private String expiresIn;

    /**
     * 当前token的唯一标识
     */
    private String jti;

}
