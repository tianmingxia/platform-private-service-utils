package cn.iald.platform.menu.service;

import cn.iald.platform.menu.dto.SysMenuInfoEditDto;
import cn.iald.platform.menu.dto.SysMenuInfoQueryDto;
import cn.iald.platform.menu.dto.SysMenuInfoSaveDto;
import cn.iald.platform.menu.entity.SysMenuInfoEntity;
import cn.iald.platform.menu.vo.SysMenuInfoVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 菜单信息表Service
 *
 * @author wangyc
 * @version 2020年12月10日 17:53:47 初始创建
 */
public interface SysMenuInfoService {

	/**
	 * 新增菜单信息表数据
	 *
	 * @param sysMenuInfoSaveDto
	 * @return
	 */
	Long save(SysMenuInfoSaveDto sysMenuInfoSaveDto);

	/**
	 * 根据条件查询菜单表实体列表Cnt
	 *
	 * @param sysMenuInfoQueryDto
	 * @return
	 */
	Long listCnt(SysMenuInfoQueryDto sysMenuInfoQueryDto);

	/**
	 * 校验菜单
	 *
	 * @param menuType
	 * @param parentId
	 */
	void checkMenu(Integer menuType, Long parentId);

	/**
	 * 修改菜单信息表数据
	 *
	 * @param sysMenuInfoEditDto
	 * @return
	 */
	int update(SysMenuInfoEditDto sysMenuInfoEditDto);

	/**
	 * 根据主键获取菜单信息表数据
	 *
	 * @param menuId
	 * @return
	 */
	SysMenuInfoEntity findByKey(Object menuId);

	/**
	 * 删除菜单
	 *
	 * @param menuId
	 * @param userId
	 * @return
	 */
	int del(Long menuId, Long userId);

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
	 * 根据查询条件获取菜单信息表数据
	 *
	 * @param sysMenuInfoQueryDto
	 * @return
	 */
	List<SysMenuInfoVo> findList(SysMenuInfoQueryDto sysMenuInfoQueryDto);

	/**
	 * 根据查询条件分页获取菜单信息表数据
	 *
	 * @param sysMenuInfoQueryDto
	 * @return
	 */
	PageInfo<SysMenuInfoEntity> findListByPage(SysMenuInfoQueryDto sysMenuInfoQueryDto);
}
