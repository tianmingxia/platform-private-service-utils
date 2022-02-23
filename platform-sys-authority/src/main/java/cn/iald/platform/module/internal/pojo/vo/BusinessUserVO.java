package cn.iald.platform.module.internal.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 经营主体用户校验VO
 *
 * @author wangyc
 * @date 2021/07/15 16:56:28
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUserVO implements Serializable {

    private static final long serialVersionUID = -5572106147479285384L;
    /**
     * 是否可访问系统，0-否 1-是
     */
    private Integer sysFlag;

    /**
     * 访问系统需携带的key
     */
    private String key;

}
