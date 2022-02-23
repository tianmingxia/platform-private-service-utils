package cn.iald.platform.feign.vo;

import lombok.Data;

/**
 * @author lht
 */
@Data
public class TokenVo {

    private String access_token;
    private String scope;
    private String token_type;
    private String expires_in;
    private String jti;

}
