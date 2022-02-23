package cn.iald.platform.module.role.pojo.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色-菜单表DO类
 *
 * @author wangyc
 * @date 2021/06/15 09:54:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_role_menu")
public class RoleMenuDO implements Serializable {

    private static final long serialVersionUID = -3504131728261445873L;
    /**
     * 主键
     */
    @TableId(value = "role_menu_id")
    private Long roleMenuId;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 菜单id
     */
    @TableField(value = "menu_id")
    private Long menuId;

    /**
     * 赋权时间
     */
    @TableField(value = "create_time")
    private Long createTime;

}
