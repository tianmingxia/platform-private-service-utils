package cn.iald.platform.login.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.iald.platform.common.constants.AuthConstants;
import cn.iald.platform.constants.SysConstants;
import cn.iald.platform.exception.BusinessException;
import cn.iald.platform.feign.OauthClient;
import cn.iald.platform.feign.PlatformAuthClient;
import cn.iald.platform.feign.dto.TokenDto;
import cn.iald.platform.feign.vo.TokenVo;
import cn.iald.platform.login.dto.LoginNoTokenDto;
import cn.iald.platform.login.service.SysLoginService;
import cn.iald.platform.login.vo.LoginRoleMenuVo;
import cn.iald.platform.login.vo.LoginUserNoTokenVo;
import cn.iald.platform.menu.dto.SysMenuInfoQueryDto;
import cn.iald.platform.menu.mapper.SysMenuInfoMapper;
import cn.iald.platform.menu.vo.SysMenuInfoVo;
import cn.iald.platform.role.mapper.SysRoleMenuMapper;
import cn.iald.platform.user.entity.SysUserInfoEntity;
import cn.iald.platform.user.service.SysUserInfoService;
import cn.iald.platform.utils.ResponseUtils;
import cn.iald.platform.vo.ResponseVo;
import cn.iald.platform.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description: 系统角色接口实现类
 * @author: wangyc
 * @create: 2020-12-12 19:27
 **/
@Slf4j
@Service
public class SysLoginServiceImpl implements SysLoginService {

	@Resource
	private PlatformAuthClient platformAuthClient;

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	@Autowired
	private SysMenuInfoMapper sysMenuInfoMapper;

	@Autowired
	private SysUserInfoService sysUserInfoService;

	@Resource
	private OauthClient oauthClient;

	@Value("${clientId:}")
	private String clientId;

	@Value("${clientSecret:}")
	private String clientSecret;

	@Value("${signingKey:}")
	private String signingKey;

	@Override
	public LoginUserNoTokenVo login(LoginNoTokenDto loginNoTokenDto) {
		LoginUserNoTokenVo loginUserVo = null;
		ResponseVo responseVo = this.platformAuthClient.login(loginNoTokenDto);
		if (ResponseUtils.ERROR.equals(responseVo.getStatus())) {
			throw new BusinessException(responseVo.getErrorMsg());
		}
		if (responseVo.getData() != null) {
			loginUserVo = JSONUtil.toBean(JSONUtil.toJsonStr(responseVo.getData()), LoginUserNoTokenVo.class);
			// 当前系统该用户状态
			SysUserInfoEntity sysUserInfoEntity = this.sysUserInfoService.findByKey(loginUserVo.getUserId());
			if (sysUserInfoEntity == null) {
				throw new BusinessException("当前用户未绑定到该系统");
			}
			if (!SysConstants.STATUS_VALID.equals(sysUserInfoEntity.getStatus())) {
				throw new BusinessException("当前用户禁止登录该系统");
			}
			List<Long> roleIdList = loginUserVo.getRoleIds();
			if (CollUtil.isNotEmpty(roleIdList)) {
				// 指定角色系统下的菜单信息
				List<LoginRoleMenuVo> loginRoleMenuVos = this.sysRoleMenuMapper.findRoleMenuCode(roleIdList);
				if (CollUtil.isNotEmpty(loginRoleMenuVos)) {
					// 符合要求的菜单code
					List<String> menuCodes = loginRoleMenuVos.stream().map(LoginRoleMenuVo::getMenuCode).filter(Objects::nonNull).collect(Collectors.toList());
					loginUserVo.setMenuCodes(menuCodes);
					// 获取token信息
					this.createToken(loginNoTokenDto, loginUserVo);
					// 获取有效的整个菜单树
					SysMenuInfoQueryDto sysMenuInfoQueryDto = new SysMenuInfoQueryDto();
					sysMenuInfoQueryDto.setStatus(SysConstants.STATUS_VALID);
					List<SysMenuInfoVo> sysMenuInfoVos = this.sysMenuInfoMapper.listVoForParam(BeanUtil.beanToMap(sysMenuInfoQueryDto));
					// 菜单组装
					sysMenuInfoVos = this.findMenuIdList(loginRoleMenuVos, sysMenuInfoVos);
					TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
					treeNodeConfig.setIdKey("menuId").setChildrenKey("children").setParentIdKey("parentId").setNameKey("menuName");
					List<Tree<String>> treeList = TreeUtil.build(sysMenuInfoVos, "0", treeNodeConfig, (treeNode, tree) -> {
						tree.setId(String.valueOf(treeNode.getMenuId()));
						tree.setParentId(String.valueOf(treeNode.getParentId()));
						tree.setName(treeNode.getMenuName());
						tree.putExtra("menuType", treeNode.getMenuType());
						tree.putExtra("menuUrl", treeNode.getMenuUrl());
						tree.putExtra("menuUrlEx", treeNode.getMenuUrlEx());
						tree.putExtra("menuIco", treeNode.getMenuIco());
					});
					loginUserVo.setMenuTreeList(treeList);
				}
			}
		}
		loginUserVo.setPassword(null);
		if (StrUtil.isNotBlank(loginUserVo.getToken())) {
			loginUserVo.setToken("Bearer " + loginUserVo.getToken());
		}
		return loginUserVo;
	}

	/**
	 * 生成token
	 *
	 * @param loginUserVo
	 */
	private void createToken(LoginNoTokenDto loginNoTokenDto, LoginUserNoTokenVo loginUserVo) {
		// jwt信息存入token
		UserVo userVo = new UserVo();
		BeanUtil.copyProperties(loginUserVo, userVo);
		userVo.setLoginIp(loginNoTokenDto.getLoginIp());
		userVo.setPassword(loginUserVo.getPassword());
		userVo.setClientId(clientId);
		userVo.setSigningKey(signingKey);
		ResponseVo responseVo = this.oauthClient.subUserInfo(JSONUtil.toJsonStr(userVo));
		if (ResponseUtils.ERROR.equals(responseVo.getStatus())) {
			throw new BusinessException(responseVo.getErrorMsg());
		}
		loginUserVo.setJwtId(String.valueOf(responseVo.getData()));
		// 获取token
		Long begin = System.currentTimeMillis();
		TokenDto tokenDto = new TokenDto();
		tokenDto.setClient_id(clientId);
		tokenDto.setClient_secret(clientSecret);
		tokenDto.setGrant_type(AuthConstants.GRANT_TYPE);
		tokenDto.setUsername(loginUserVo.getJwtId());
		tokenDto.setPassword(loginNoTokenDto.getPassword());
		TokenVo tokenVo = this.oauthClient.token(BeanUtil.beanToMap(tokenDto));
		log.info("token:" + JSONUtil.toJsonStr(tokenVo));
		loginUserVo.setToken(tokenVo.getAccess_token());
		log.info("获取token时间" + (System.currentTimeMillis() - begin));
	}

	/**
	 * 获取符合要求的父节点
	 *
	 * @param loginRoleMenuVos
	 * @param sysMenuInfoVos
	 * @return
	 */
	private List<SysMenuInfoVo> findMenuIdList(List<LoginRoleMenuVo> loginRoleMenuVos, List<SysMenuInfoVo> sysMenuInfoVos) {
		Long begin = System.currentTimeMillis();
		List<CharSequence> menuIdList = new ArrayList<>();
		// 符合要求的菜单id
		List<Long> menuIds = loginRoleMenuVos.stream().map(LoginRoleMenuVo::getMenuId).filter(menuId -> menuId != null).collect(Collectors.toList());
		List<Tree<String>> treeList = TreeUtil.build(sysMenuInfoVos, "0", (treeNode, tree) -> {
			tree.setId(String.valueOf(treeNode.getMenuId()));
			tree.setParentId(String.valueOf(treeNode.getParentId()));
			tree.setName(String.valueOf(treeNode.getMenuId()));
		});
		for (Tree<String> strTree : treeList) {
			for (Long menuId : menuIds) {
				List<CharSequence> pIdList = strTree.getParentsName(String.valueOf(menuId), true);
				CollUtil.addAllIfNotContains(menuIdList, pIdList);
			}
		}
		final List<CharSequence> menuIdListTemp = menuIdList.stream().distinct().collect(Collectors.toList());
		log.info("获取符合要求的父节点时间" + (System.currentTimeMillis() - begin));
		return sysMenuInfoVos.stream().filter(sysMenuVo -> (menuIdListTemp.contains(String.valueOf(sysMenuVo.getMenuId())))).collect(Collectors.toList());
	}
}
