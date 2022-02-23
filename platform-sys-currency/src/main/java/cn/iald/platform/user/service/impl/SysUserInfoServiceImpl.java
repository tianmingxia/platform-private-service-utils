package cn.iald.platform.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import cn.iald.platform.constants.SysConstants;
import cn.iald.platform.exception.BusinessException;
import cn.iald.platform.feign.PlatformAuthClient;
import cn.iald.platform.user.dto.SysUserEditStatusDto;
import cn.iald.platform.user.dto.SysUserInfoEditDto;
import cn.iald.platform.user.dto.SysUserInfoSaveDto;
import cn.iald.platform.user.dto.client.*;
import cn.iald.platform.user.entity.SysUserInfoEntity;
import cn.iald.platform.user.mapper.SysUserInfoMapper;
import cn.iald.platform.user.service.SysUserInfoService;
import cn.iald.platform.user.vo.PsUserVo;
import cn.iald.platform.utils.ResponseUtils;
import cn.iald.platform.utils.TimeUtils;
import cn.iald.platform.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户信息表Service实现
 *
 * @author wangyc
 * @version 2020年12月14日 10:16:58 初始创建
 */
@Slf4j
@Service
public class SysUserInfoServiceImpl implements SysUserInfoService {

	@Autowired
	private SysUserInfoMapper sysUserInfoMapper;

	@Resource
	private PlatformAuthClient platformAuthClient;

	@Autowired
	private SysUserInfoService sysUserInfoService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Long save(SysUserInfoEntity sysUserInfoEntity) {
		sysUserInfoEntity.setStatus(SysConstants.STATUS_VALID);
		sysUserInfoEntity.setDelFlag(SysConstants.STATUS_VALID);
		sysUserInfoEntity.setCreateTime(TimeUtils.getCurrentTime());
		sysUserInfoEntity.setUpdateTime(TimeUtils.getCurrentTime());
		int resCnt = this.sysUserInfoMapper.save(sysUserInfoEntity);
		if (resCnt < 1) {
			throw new BusinessException("用户添加失败");
		}
		return sysUserInfoEntity.getUserId();
	}

	@Override
	public ResponseVo saveUser(SysUserInfoSaveDto sysUserInfoSaveDto) {
		// 配置中心添加用户
		PsUserSaveNoTokenDto psUserSaveNoTokenDto = new PsUserSaveNoTokenDto();
		BeanUtil.copyProperties(sysUserInfoSaveDto, psUserSaveNoTokenDto);
		ResponseVo responseVo = this.platformAuthClient.saveUser(psUserSaveNoTokenDto);
		if (ResponseUtils.SUCCESS.equals(responseVo.getStatus()) && responseVo.getData() != null ) {
			Long userId = Long.valueOf(String.valueOf(responseVo.getData()));
			SysUserInfoEntity sysUserInfoEntity = new SysUserInfoEntity();
			BeanUtil.copyProperties(sysUserInfoSaveDto, sysUserInfoEntity);
			sysUserInfoEntity.setUserId(userId);
			return ResponseUtils.success(this.sysUserInfoService.save(sysUserInfoEntity));
		}
		return responseVo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int update(SysUserInfoEditDto sysUserInfoEditDto) {
		SysUserInfoEntity sysUserInfoEntity = new SysUserInfoEntity();
		BeanUtil.copyProperties(sysUserInfoEditDto, sysUserInfoEntity);
		sysUserInfoEntity.setUpdateTime(TimeUtils.getCurrentTime());
		return this.sysUserInfoMapper.update(sysUserInfoEntity);
	}

	@Override
	public ResponseVo updateUser(SysUserInfoEditDto sysUserInfoEditDto) {
		// 配置中心修改用户
		PsUserEditNoTokenDto psUserEditDto = new PsUserEditNoTokenDto();
		BeanUtil.copyProperties(sysUserInfoEditDto, psUserEditDto);
		ResponseVo responseVo = this.platformAuthClient.editUser(psUserEditDto);
		if (ResponseUtils.SUCCESS_VAL.equals(responseVo.getCode())) {
			// 本地是否存在
			SysUserInfoEntity sysUserInfoEntity = this.sysUserInfoMapper.getObjectById(sysUserInfoEditDto.getUserId());
			if (sysUserInfoEntity != null && sysUserInfoEntity.getUserId() != null) {
				this.sysUserInfoService.update(sysUserInfoEditDto);
			} else {
				sysUserInfoEntity = new SysUserInfoEntity();
				BeanUtil.copyProperties(sysUserInfoEditDto, sysUserInfoEntity);
				return ResponseUtils.success(this.sysUserInfoService.save(sysUserInfoEntity));
			}
		}
		return responseVo;
	}

	@Override
	public String getUserNameById(Object id) {
		return this.sysUserInfoMapper.getUserNameById(id);
	}

	@Override
	public SysUserInfoEntity findByKey(Object userId) {
		return this.sysUserInfoMapper.getObjectById(userId);
	}

	@Override
	public ResponseVo findList(PsUserQueryDto psUserQueryDto) {
		return this.platformAuthClient.findUsers(psUserQueryDto);
	}

	@Override
	public PsUserVo findByUserId(Long userId) {
		PsUserVo psUserVo = null;
		ResponseVo responseVo = this.platformAuthClient.findUserByKey(userId);
		if (ResponseUtils.SUCCESS.equals(responseVo.getStatus())) {
			if (responseVo.getData() != null) {
				psUserVo = JSONUtil.toBean(JSONUtil.toJsonStr(responseVo.getData()), PsUserVo.class);
				SysUserInfoEntity sysUserInfoEntity = this.sysUserInfoMapper.getObjectById(userId);
				if (sysUserInfoEntity == null) {
					psUserVo.setSysStatus(SysConstants.STATUS_DEL);
				} else {
					psUserVo.setSysStatus(sysUserInfoEntity.getStatus());
				}
			}
		}
		return psUserVo;
	}

	@Override
	public ResponseVo saveUserRoleBySys(PsUserRoleSaveDto psUserRoleSaveDto) {
		this.sysUserInfoService.pullUser(psUserRoleSaveDto.getUserId());
		return this.platformAuthClient.saveUserRoleBySys(psUserRoleSaveDto);
	}

	@Override
	public ResponseVo findUserRole(PsUserRoleQueryDto psUserRoleQueryDto) {
		return this.platformAuthClient.findUserRole(psUserRoleQueryDto);
	}

	@Override
	public ResponseVo findAllRole(PsUserRoleQueryDto psUserRoleQueryDto) {
		return this.platformAuthClient.findAllRole(psUserRoleQueryDto);
	}

	@Override
	public int editStatus(SysUserEditStatusDto sysUserEditStatusDto) {
		this.sysUserInfoService.pullUser(sysUserEditStatusDto.getUserId());
		if (!SysConstants.STATUS_VALID.equals(sysUserEditStatusDto.getStatus())) {
			sysUserEditStatusDto.setStatus(SysConstants.STATUS_DISABLE);
		}
		SysUserInfoEditDto sysUserInfoEditDto = new SysUserInfoEditDto();
		sysUserInfoEditDto.setUserId(sysUserEditStatusDto.getUserId());
		sysUserInfoEditDto.setUpdateBy(sysUserEditStatusDto.getUpdateBy());
		sysUserInfoEditDto.setStatus(sysUserEditStatusDto.getStatus());
		return this.sysUserInfoService.update(sysUserInfoEditDto);
	}

	/**
	 * 用户同步到本地
	 *
	 * @param userId
	 */
	@Override
	public void pullUser(Long userId) {
		// 本地是否存在
		SysUserInfoEntity sysUserInfoEntity = this.sysUserInfoMapper.getObjectById(userId);
		if (sysUserInfoEntity == null) {
			ResponseVo responseVo = this.platformAuthClient.findUserByKey(userId);
			if (ResponseUtils.SUCCESS.equals(responseVo.getStatus())) {
				if (responseVo.getData() != null) {
					PsUserVo psUserVo = JSONUtil.toBean(JSONUtil.toJsonStr(responseVo.getData()), PsUserVo.class);
					sysUserInfoEntity = new SysUserInfoEntity();
					BeanUtil.copyProperties(psUserVo, sysUserInfoEntity);
					this.sysUserInfoService.save(sysUserInfoEntity);
					log.info("同步用户到本地：" + JSONUtil.toJsonStr(psUserVo));
				}
			}
		}
	}
}
