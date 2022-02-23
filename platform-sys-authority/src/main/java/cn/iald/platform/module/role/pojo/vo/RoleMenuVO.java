package cn.iald.platform.module.role.pojo.vo;

import cn.hutool.core.lang.tree.Tree;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色菜单VO
 *
 * @author wangyc
 * @date 2021/07/13 13:02:25
 **/
@Data
public class RoleMenuVO implements Serializable {
    private static final long serialVersionUID = 8139609008583110419L;
    /**
     * 菜单权限Code
     */
    private List<String> menuCodeList;

    /**
     * 所属系统权限菜单
     */
    private List<Tree<String>> menuTreeList;
}
