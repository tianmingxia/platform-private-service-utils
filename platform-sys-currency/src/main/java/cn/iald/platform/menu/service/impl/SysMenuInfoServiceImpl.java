package cn.iald.platform.menu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.iald.platform.common.constants.AuthConstants;
import cn.iald.platform.constants.SysConstants;
import cn.iald.platform.exception.BusinessException;
import cn.iald.platform.menu.dto.SysMenuInfoEditDto;
import cn.iald.platform.menu.dto.SysMenuInfoQueryDto;
import cn.iald.platform.menu.dto.SysMenuInfoSaveDto;
import cn.iald.platform.menu.entity.SysMenuInfoEntity;
import cn.iald.platform.menu.mapper.SysMenuInfoMapper;
import cn.iald.platform.menu.service.SysMenuInfoService;
import cn.iald.platform.menu.vo.SysMenuInfoVo;
import cn.iald.platform.role.mapper.SysRoleMenuMapper;
import cn.iald.platform.utils.IdWorker;
import cn.iald.platform.utils.TimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单信息表Service实现
 *
 * @author wangyc
 * @version 2020年12月10日 17:53:47 初始创建
 */
@Service
public class SysMenuInfoServiceImpl implements SysMenuInfoService {

	@Autowired
	private SysMenuInfoMapper sysMenuInfoMapper;

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Long save(SysMenuInfoSaveDto sysMenuInfoSaveDto) {
		SysMenuInfoEntity sysMenuInfoEntity = new SysMenuInfoEntity();
		BeanUtil.copyProperties(sysMenuInfoSaveDto, sysMenuInfoEntity);
		sysMenuInfoEntity.setMenuId(IdWorker.getLongId());
		sysMenuInfoEntity.setStatus(SysConstants.STATUS_VALID);
		sysMenuInfoEntity.setDelFlag(SysConstants.STATUS_VALID);
		sysMenuInfoEntity.setCreateTime(TimeUtils.getCurrentTime());
		sysMenuInfoEntity.setUpdateTime(TimeUtils.getCurrentTime());
		int resCnt = this.sysMenuInfoMapper.save(sysMenuInfoEntity);
		if (resCnt < 1) {
			throw new BusinessException("菜单添加失败");
		}
		return sysMenuInfoEntity.getMenuId();
	}

	@Override
	public Long listCnt(SysMenuInfoQueryDto sysMenuInfoQueryDto) {
		return this.sysMenuInfoMapper.listCnt(sysMenuInfoQueryDto);
	}

	@Override
	public void checkMenu(Integer menuType, Long parentId) {
		Integer parentMenuType = null;
		if (!AuthConstants.MENU_PARENT_ID.equals(parentId)) {
			SysMenuInfoEntity sysMenuInfoEntity = this.sysMenuInfoMapper.getObjectById(parentId);
			parentMenuType = sysMenuInfoEntity.getMenuType();
		}
		switch (String.valueOf(menuType)) {
			case AuthConstants.MENU_TYPE4_STR:
				if (AuthConstants.MENU_PARENT_ID.equals(parentId)) {
					throw new BusinessException("按钮的上级不能是顶级系统");
				}
				if (AuthConstants.MENU_TYPE4.equals(parentMenuType)) {
					throw new BusinessException("按钮的上级不能是按钮");
				}
				if (AuthConstants.MENU_TYPE3.equals(parentMenuType)) {
					throw new BusinessException("按钮的上级不能是接口");
				}
				break;
			case AuthConstants.MENU_TYPE3_STR:
				if (AuthConstants.MENU_TYPE4.equals(parentMenuType)) {
					throw new BusinessException("接口的上级不能是按钮");
				}
				break;
			case AuthConstants.MENU_TYPE2_STR:
				if (AuthConstants.MENU_TYPE4.equals(parentMenuType)) {
					throw new BusinessException("菜单的上级不能是按钮");
				}
				if (AuthConstants.MENU_TYPE3.equals(parentMenuType)) {
					throw new BusinessException("菜单的上级不能是接口");
				}
				break;
			case AuthConstants.MENU_TYPE1_STR:
				if (AuthConstants.MENU_TYPE4.equals(parentMenuType)) {
					throw new BusinessException("模块的上级不能是按钮");
				}
				if (AuthConstants.MENU_TYPE3.equals(parentMenuType)) {
					throw new BusinessException("模块的上级不能是接口");
				}
				if (AuthConstants.MENU_TYPE2.equals(parentMenuType)) {
					throw new BusinessException("模块的上级不能是菜单");
				}
				break;
			default:
				break;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int update(SysMenuInfoEditDto sysMenuInfoEditDto) {
		SysMenuInfoEntity sysMenuInfoEntity = new SysMenuInfoEntity();
		BeanUtil.copyProperties(sysMenuInfoEditDto, sysMenuInfoEntity);
		sysMenuInfoEntity.setUpdateTime(TimeUtils.getCurrentTime());
		return this.sysMenuInfoMapper.update(sysMenuInfoEntity);
	}

	@Override
	public SysMenuInfoEntity findByKey(Object menuId) {
		return this.sysMenuInfoMapper.getObjectById(menuId);
	}

	@Override
	public int del(Long menuId, Long userId) {
		SysMenuInfoEntity sysMenuInfoEntity = new SysMenuInfoEntity();
		sysMenuInfoEntity.setMenuId(menuId);
		sysMenuInfoEntity.setDelFlag(SysConstants.STATUS_DEL);
		sysMenuInfoEntity.setUpdateBy(userId);
		sysMenuInfoEntity.setUpdateTime(TimeUtils.getCurrentTime());
		return this.sysMenuInfoMapper.update(sysMenuInfoEntity);
	}

	@Override
	public Long findRoleMenuCnt(Long menuId) {
		return this.sysRoleMenuMapper.findRoleMenuCnt(menuId);
	}

	@Override
	public List<String> findRoleNameByMenuId(Long menuId) {
		return this.sysRoleMenuMapper.findRoleNameByMenuId(menuId);
	}

	@Override
	public List<SysMenuInfoVo> findList(SysMenuInfoQueryDto sysMenuInfoQueryDto) {
		if (SysConstants.CHILDFLAG1.equals(sysMenuInfoQueryDto.getChildFlag())) {
			return this.sysMenuInfoMapper.listVoForParam(BeanUtil.beanToMap(sysMenuInfoQueryDto));
		}
		List<SysMenuInfoVo> sysMenuInfoVos = null;
		if (SysConstants.CHILDFLAG2.equals(sysMenuInfoQueryDto.getChildFlag())) {
			sysMenuInfoVos = this.sysMenuInfoMapper.listVoForParam(BeanUtil.beanToMap(sysMenuInfoQueryDto));
			if (CollUtil.isNotEmpty(sysMenuInfoVos)) {
				SysMenuInfoQueryDto sysMenuInfoQueryDtoTemp;
				for (SysMenuInfoVo menuInfoVo : sysMenuInfoVos) {
					if (menuInfoVo.getSubFlag()) {
						sysMenuInfoQueryDtoTemp = new SysMenuInfoQueryDto();
						sysMenuInfoQueryDtoTemp.setParentId(menuInfoVo.getMenuId());
						menuInfoVo.setChildren(this.sysMenuInfoMapper.listVoForParam(BeanUtil.beanToMap(sysMenuInfoQueryDtoTemp)));
					}
				}
			}
		}
		return sysMenuInfoVos;
	}

	@Override
	public PageInfo<SysMenuInfoEntity> findListByPage(SysMenuInfoQueryDto sysMenuInfoQueryDto) {
		PageHelper.startPage(sysMenuInfoQueryDto.getPageNum(), sysMenuInfoQueryDto.getPageSize());
		List<SysMenuInfoEntity> sysMenuInfoList = this.sysMenuInfoMapper.listForParam(BeanUtil.beanToMap(sysMenuInfoQueryDto));
		PageInfo<SysMenuInfoEntity> pageInfo = new PageInfo<>(sysMenuInfoList);
		return pageInfo;
	}

}
