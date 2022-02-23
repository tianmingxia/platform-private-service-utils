package cn.iald.platform.module.menu.pojo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单表DO类
 *
 * @author wangyc
 * @date 2021/06/11 18:07:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_menu")
public class MenuDO implements Serializable {

    private static final long serialVersionUID = -1971939248086363865L;
    /**
     * 主键
     */
    @TableId(value = "menu_id")
    private Long menuId;

    /**
     * 菜单编码，具有唯一性，同一系列权限的菜单使用同一编码
     */
    @TableField(value = "menu_code")
    private String menuCode;

    /**
     * 菜单名称
     */
    @TableField(value = "menu_name")
    private String menuName;

    /**
     * 跳转地址
     */
    @TableField(value = "menu_url")
    private String menuUrl;

    /**
     * 地址扩展字段
     */
    @TableField(value = "menu_url_ex")
    private String menuUrlEx;

    /**
     * 类型（1模块 2菜单 3接口 4按钮）
     */
    @TableField(value = "menu_type")
    private Integer menuType;

    /**
     * 上级菜单
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 显示顺序
     */
    @TableField(value = "sort_no")
    private Integer sortNo;

    /**
     * 菜单图标
     */
    @TableField(value = "menu_ico")
    private String menuIco;

    /**
     * 所属平台id，对应字典表
     */
    @TableField(value = "platform_id")
    private Integer platformId;

    /**
     * 所属系统id，对应字典表
     */
    @TableField(value = "system_id")
    private Integer systemId;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 状态（1有效 -1禁用）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 修改人
     */
    @TableField(value = "update_by")
    private Long updateBy;

    /**
     * 是否删除
     */
    @TableField(value = "is_del_flag")
    private Integer delFlag;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Long createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Long updateTime;

}
