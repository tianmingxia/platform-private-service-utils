package cn.iald.platform.role.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.iald.platform.exception.BusinessException;
import cn.iald.platform.feign.PlatformAuthClient;
import cn.iald.platform.role.dto.SysRoleMenuSaveDto;
import cn.iald.platform.role.dto.client.PsRoleDelNoTokenDto;
import cn.iald.platform.role.dto.client.PsRoleEditNoTokenDto;
import cn.iald.platform.role.dto.client.PsRoleQueryDto;
import cn.iald.platform.role.dto.client.PsRoleSaveNoTokenDto;
import cn.iald.platform.role.entity.SysRoleMenuEntity;
import cn.iald.platform.role.mapper.SysRoleMenuMapper;
import cn.iald.platform.role.service.SysRoleService;
import cn.iald.platform.role.vo.SysRoleMenuVo;
import cn.iald.platform.utils.IdWorker;
import cn.iald.platform.utils.ResponseUtils;
import cn.iald.platform.utils.TimeUtils;
import cn.iald.platform.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 系统角色接口实现类
 * @author: wangyc
 * @create: 2020-12-12 19:27
 **/
@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Resource
	private PlatformAuthClient platformAuthClient;

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	@Override
	public ResponseVo save(PsRoleSaveNoTokenDto psRoleSaveDto) {
		return this.platformAuthClient.saveRole(psRoleSaveDto);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseVo edit(PsRoleEditNoTokenDto psRoleEditDto) {
		// 更新角色名称
		SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
		BeanUtil.copyProperties(psRoleEditDto, sysRoleMenuEntity);
		this.sysRoleMenuMapper.updateRoleName(sysRoleMenuEntity);
		ResponseVo responseVo = this.platformAuthClient.editRole(psRoleEditDto);
		if (ResponseUtils.ERROR.equals(responseVo.getStatus())) {
			throw new BusinessException(responseVo.getErrorMsg());
		}
		return responseVo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseVo del(PsRoleDelNoTokenDto psRoleDelDto) {
		// 删除分配的菜单
		this.sysRoleMenuMapper.delByRoleId(psRoleDelDto.getRoleId());
		ResponseVo responseVo = this.platformAuthClient.delRole(psRoleDelDto);
		if (ResponseUtils.ERROR.equals(responseVo.getStatus())) {
			throw new BusinessException(responseVo.getErrorMsg());
		}
		return responseVo;
	}

	@Override
	public ResponseVo findRoleByKey(Long roleId) {
		return this.platformAuthClient.findRoleByKey(roleId);
	}

	@Override
	public ResponseVo findRoles(PsRoleQueryDto psRoleQueryDto) {
		return this.platformAuthClient.findRoles(psRoleQueryDto);
	}

	@Override
	public int saveRoleMenu(SysRoleMenuSaveDto sysRoleMenuSaveDto) {
		// 删除角色原有的菜单
		int resCnt = this.sysRoleMenuMapper.delByRoleId(sysRoleMenuSaveDto.getRoleId());
		// 完善要保存的数据
		List<Long> menuIds = sysRoleMenuSaveDto.getMenuIdList();
		if (CollUtil.isNotEmpty(menuIds)) {
			List<SysRoleMenuEntity> sysRoleMenuEntities = new ArrayList<>();
			for (Long menuId : menuIds) {
				SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity(IdWorker.getLongId(), sysRoleMenuSaveDto.getRoleId(),
						sysRoleMenuSaveDto.getRoleName(), menuId, TimeUtils.getCurrentTime());
				sysRoleMenuEntities.add(sysRoleMenuEntity);
			}
			return this.sysRoleMenuMapper.batchSave(sysRoleMenuEntities);
		}
		return resCnt;
	}

	@Override
	public List<SysRoleMenuEntity> findMenuByRoleId(Long roleId) {
		SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
		sysRoleMenuEntity.setRoleId(roleId);
		return this.sysRoleMenuMapper.listForParam(BeanUtil.beanToMap(sysRoleMenuEntity));
	}

	@Override
	public List<SysRoleMenuVo> findMenuTreeByRoleId(Long roleId) {
		SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
		sysRoleMenuEntity.setRoleId(roleId);
		return this.sysRoleMenuMapper.listVoForParam(BeanUtil.beanToMap(sysRoleMenuEntity));
	}

}
