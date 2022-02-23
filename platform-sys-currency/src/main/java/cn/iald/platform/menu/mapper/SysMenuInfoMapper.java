package cn.iald.platform.menu.mapper;

import cn.iald.platform.menu.dto.SysMenuInfoQueryDto;
import cn.iald.platform.menu.entity.SysMenuInfoEntity;
import cn.iald.platform.menu.vo.SysMenuInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 菜单信息表Mapper
 *
 * @author wangyc
 * @version 2020年12月10日 17:53:47 初始创建
 */
@Mapper
public interface SysMenuInfoMapper {

	/**
	 * 新增菜单信息表数据
	 *
	 * @param sysMenuInfoEntity
	 * @return
	 */
	int save(SysMenuInfoEntity sysMenuInfoEntity);

	/**
	 * 根据id查询菜单信息表数据
	 *
	 * @param id
	 * @return
	 */
	SysMenuInfoEntity getObjectById(Object id);

	/**
	 * 更新菜单信息表数据
	 *
	 * @param sysMenuInfoEntity
	 * @return
	 */
	int update(SysMenuInfoEntity sysMenuInfoEntity);

	/**
	 * 根据条件查询菜单信息表实体列表
	 *
	 * @param paramMap
	 * @return
	 */
	List<SysMenuInfoEntity> listForParam(Map<String, Object> paramMap);

	/**
	 * 根据条件查询菜单信息表Vo列表
	 *
	 * @param paramMap
	 * @return
	 */
	List<SysMenuInfoVo> listVoForParam(Map<String, Object> paramMap);

	/**
	 * 根据条件查询菜单表实体列表Cnt
	 *
	 * @param sysMenuInfoQueryDto
	 * @return
	 */
	Long listCnt(SysMenuInfoQueryDto sysMenuInfoQueryDto);
}