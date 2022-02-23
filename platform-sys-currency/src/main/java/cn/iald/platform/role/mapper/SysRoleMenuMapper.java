package cn.iald.platform.role.mapper;

import cn.iald.platform.login.vo.LoginRoleMenuVo;
import cn.iald.platform.role.dto.SysMenuBatchEditDto;
import cn.iald.platform.role.entity.SysRoleMenuEntity;
import cn.iald.platform.role.vo.SysRoleMenuVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 角色-菜单关联表Mapper
 *
 * @author wangyc
 * @version 2020年12月14日 09:57:33 初始创建
 */
@Mapper
public interface SysRoleMenuMapper {

	/**
	 * 新增角色-菜单关联表数据
	 *
	 * @param sysRoleMenuEntity
	 * @return
	 */
	int save(SysRoleMenuEntity sysRoleMenuEntity);

	/**
	 * 根据id查询角色-菜单关联表数据
	 *
	 * @param id
	 * @return
	 */
	SysRoleMenuEntity getObjectById(Object id);

	/**
	 * 更新角色-菜单关联表数据
	 *
	 * @param sysRoleMenuEntity
	 * @return
	 */
	int update(SysRoleMenuEntity sysRoleMenuEntity);

	/**
	 * 根据条件查询角色-菜单关联表实体列表
	 *
	 * @param paramMap
	 * @return
	 */
	List<SysRoleMenuEntity> listForParam(Map<String, Object> paramMap);

	/**
	 * 根据条件查询角色-菜单关联表Vo列表
	 *
	 * @param paramMap
	 * @return
	 */
	List<SysRoleMenuVo> listVoForParam(Map<String, Object> paramMap);

	/**
	 * 根据菜单id删除角色对应的菜单
	 *
	 * @param sysMenuBatchEditDto
	 * @return
	 */
	int delByMenuId(SysMenuBatchEditDto sysMenuBatchEditDto);

	/**
	 * 根据角色id删除角色对应的菜单
	 *
	 * @param roleId
	 * @return
	 */
	int delByRoleId(Long roleId);

	/**
	 * 批量新增角色-菜单表数据
	 *
	 * @param sysRoleMenuEntities
	 * @return
	 */
	int batchSave(List<SysRoleMenuEntity> sysRoleMenuEntities);

	/**
	 * 菜单是否在角色中使用
	 *
	 * @param menuId
	 * @return
	 */
	Long findRoleMenuCnt(Long menuId);

	/**
	 * 获取菜单对应的角色名称
	 *
	 * @param menuId
	 * @return
	 */
	List<String> findRoleNameByMenuId(Long menuId);

	/**
	 * 获取指定角色系统下的菜单信息
	 *
	 * @param roleIdList
	 * @return
	 */
	List<LoginRoleMenuVo> findRoleMenuCode(List<Long> roleIdList);

	/**
	 * 更新角色名称
	 *
	 * @param sysRoleMenuEntity
	 * @return
	 */
	int updateRoleName(SysRoleMenuEntity sysRoleMenuEntity);
}