package cn.iald.platform.module.login.pojo.vo;

import cn.hutool.core.lang.tree.Tree;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 经营主体用户内部接口登录VO
 *
 * @author wangyc
 * @date 2021/06/21 15:20:48
 **/
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BusinessLoginVO implements Serializable {

    private static final long serialVersionUID = 4365199082773917632L;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 所属经营主体id
     */
    private Long businessId;

    /**
     * 登录名（唯一）
     */
    private String username;

    /**
     * 真实姓名（加密）
     */
    private String realname;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 备注
     */
    private String remark;

    /**
     * 当前用户指定系统角色id
     */
    private List<Long> roleIds;

    /**
     * 菜单权限Code
     */
    private List<String> menuCodeList;

    /**
     * 所属系统权限菜单
     */
    private List<Tree<String>> menuTreeList;

    /**
     * jwt辅助id
     */
    private String jwtId;

    /**
     * token
     */
    private String token;

}
