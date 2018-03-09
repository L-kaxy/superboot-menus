/**
 * Copyright (c) 2007-2017 Wteam.  All rights reserved. 网维网络技术创业团队 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.menu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wteam.superboot.core.entity.po.PageinfoPo;
import com.wteam.superboot.core.entity.po.SuperPersistentObject;
import com.wteam.superboot.core.entity.po.UserPo;
import com.wteam.superboot.core.enums.ResultEnum;
import com.wteam.superboot.core.exception.SuperException;
import com.wteam.superboot.core.helper.ResultHelper;
import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.menu.entity.po.MenuItemPo;
import com.wteam.superboot.menu.entity.po.MenuPo;
import com.wteam.superboot.menu.entity.vo.MenuItemVo;
import com.wteam.superboot.menu.entity.vo.MenuVo;
import com.wteam.superboot.menu.repository.MenuItemRepository;
import com.wteam.superboot.menu.repository.MenuRepository;
import com.wteam.superboot.security.entity.po.AuthitemmapPo;
import com.wteam.superboot.security.entity.po.PermissionresourcemapPo;
import com.wteam.superboot.security.entity.po.ResourcePo;
import com.wteam.superboot.security.entity.po.ResourcetypePo;
import com.wteam.superboot.security.entity.po.UserauthitemmapPo;
import com.wteam.superboot.security.repository.AuthitemmapRepository;
import com.wteam.superboot.security.repository.PermissionresourcemapRepository;
import com.wteam.superboot.security.repository.ResourceRepository;
import com.wteam.superboot.security.repository.ResourcetypeRepository;
import com.wteam.superboot.security.repository.UserauthitemmapRepository;

/**
 * 菜单项Service类.
 * 
 * @author 罗佳欣
 *
 */
@Service
@Transactional
public class MenuService {

	/**
	 * 注入menuItemRepository.
	 */
	@Autowired
	private MenuItemRepository menuItemRepository;

	/**
	 * 注入menuItemRepository.
	 */
	@Autowired
	private MenuRepository menuRepository;

	/**
	 * 注入userauthitemmapRepository.
	 */
	@Autowired
	private UserauthitemmapRepository userauthitemmapRepository;

	/**
	 * 注入authitemmapRepository.
	 */
	@Autowired
	private AuthitemmapRepository authitemmapRepository;

	/**
	 * 注入resourcetypeRepository.
	 */
	@Autowired
	private ResourcetypeRepository resourcetypeRepository;

	/**
	 * 注入permissionresourcemapRepository.
	 */
	@Autowired
	private PermissionresourcemapRepository permissionresourcemapRepository;

	/**
	 * 注入resourceRepository.
	 */
	@Autowired
	private ResourceRepository resourceRepository;

	/**
	 * 批量添加菜单.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ResultMessage addMenuItemByList(final List<MenuPo> list, final UserPo currentUser) throws Exception {
		if (list == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (currentUser == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		MenuPo valid = null;
		List<MenuPo> sameName = new ArrayList<MenuPo>();
		for (MenuPo po : list) {
			valid = new MenuPo();
			valid.setMenuname(po.getMenuname());
			if (menuRepository.hasNonDeleteEntity(valid)) {
				sameName.add(po);
			} else {
				menuRepository.addEntity(po, currentUser);
			}
		}

		ResultMessage rs = ResultHelper.result(ResultEnum.ADD_SUCCESS);

		if (!sameName.isEmpty()) {
			Map<String, Object> resultParm = new HashMap<String, Object>();
			resultParm.put("sameName", sameName);
			rs.setResultParm(resultParm);
		}

		return rs;
	}

	/**
	 * 批量删除菜单.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ResultMessage deleteMenuByList(final List<MenuPo> list, final UserPo currentUser) throws Exception {
		if (list == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (currentUser == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		MenuItemPo valid = null;
		List<MenuPo> notEmpty = new ArrayList<MenuPo>();
		for (MenuPo po : list) {
			valid = new MenuItemPo();
			valid.setMenuid(po.getMenuid());
			if (menuItemRepository.hasEntity(valid)) {
				notEmpty.add(po);
			} else {
				menuRepository.deleteEntity(po);
			}
		}

		ResultMessage rs = ResultHelper.result(ResultEnum.DEL_SUCCESS);

		if (!notEmpty.isEmpty()) {
			Map<String, Object> resultParm = new HashMap<String, Object>();
			resultParm.put("notEmpty", notEmpty);
			rs.setResultParm(resultParm);
		}

		return rs;
	}

	/**
	 * 批量编辑菜单.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ResultMessage editMenuByList(final List<MenuPo> list, final UserPo currentUser) throws Exception {
		if (list == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (currentUser == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		MenuPo valid = null;
		List<MenuPo> sameName = new ArrayList<MenuPo>();
		for (MenuPo po : list) {
			valid = new MenuPo();
			valid.setMenuname(po.getMenuname());
			MenuPo tempValid = menuRepository.queryEntity(valid);
			if (tempValid != null && tempValid.getMenuid() != po.getMenuid()) {
				sameName.add(po);
			} else {
				menuRepository.editEntity(po, currentUser);
			}
		}

		ResultMessage rs = ResultHelper.result(ResultEnum.EDIT_SUCCESS);

		if (!sameName.isEmpty()) {
			Map<String, Object> resultParm = new HashMap<String, Object>();
			resultParm.put("sameName", sameName);
			rs.setResultParm(resultParm);
		}

		return rs;
	}

	/**
	 * 菜单分页.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ResultMessage pageMenu(final PageinfoPo pageinfo, final MenuPo likePo) throws Exception {
		if (pageinfo == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (likePo == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (pageinfo.getSortFieldNames() == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (pageinfo.getOrder() == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		Page<MenuPo> pageResult = menuRepository.pageNonDeleteEntity(pageinfo, new MenuPo(), likePo);

		Map<String, Object> parm = new HashMap<String, Object>();

		List<MenuVo> resultList = new ArrayList<MenuVo>();
		MenuVo tempVo = null;
		for (SuperPersistentObject po : pageResult.getContent()) {
			tempVo = new MenuVo();
			tempVo.poToVo(po);
			resultList.add(tempVo);
		}

		parm.put("pageList", resultList);
		parm.put("totalCount", pageResult.getTotalElements());

		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);

		return rs;
	}

	/**
	 * 获取当前用户指定菜单.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ResultMessage getCurrentUserMenuItemList(final MenuPo menuPo, final UserPo userPo) throws Exception {
		if (menuPo == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (userPo == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		// 获取指定用户的所有权限编号
		UserauthitemmapPo userauth = new UserauthitemmapPo();
		userauth.setUserid(userPo.getUserid());
		AuthitemmapPo rolePermission = null;
		List<Long> permissionIds = new ArrayList<Long>();
		for (UserauthitemmapPo role : userauthitemmapRepository.queryNonDeleteList(userauth)) {
			rolePermission = new AuthitemmapPo();
			rolePermission.setParentid(role.getAuthitemid());
			for (AuthitemmapPo temp : authitemmapRepository.queryNonDeleteList(rolePermission)) {
				if (!permissionIds.contains(temp.getChildid())) {
					permissionIds.add(temp.getChildid());
				}
			}
		}

		// 获取指定用户的所有指定菜单菜单项
		ResourcetypePo type = new ResourcetypePo();
		type.setResourcetypename("menuitem");
		type = resourcetypeRepository.queryEntity(type);
		PermissionresourcemapPo permisssionresource = null;
		ResourcePo resource = null;
		MenuPo menu = new MenuPo();
		menu.setMenuname(menuPo.getMenuname());
		menu = menuRepository.queryEntity(menu);
		MenuItemPo menuItem = null;
		Map<Long, MenuItemPo> menuItems = new HashMap<Long, MenuItemPo>();
		for (Long permissionId : permissionIds) {
			permisssionresource = new PermissionresourcemapPo();
			permisssionresource.setPermissionid(permissionId);
			for (PermissionresourcemapPo temp : permissionresourcemapRepository
					.queryNonDeleteList(permisssionresource)) {
				resource = resourceRepository.getEntityById(ResourcePo.class, temp.getResourceid());
				if (!menuItems.containsKey(resource.getResourceid())
						&& resource.getResourcetypeid().equals(type.getResourcetypeid())) {
					menuItem = menuItemRepository.getEntityById(MenuItemPo.class, resource.getRealid());
					if (menuItem.getMenuid().equals(menu.getMenuid())) {
						menuItems.put(temp.getResourceid(), menuItem);
					}
				}
			}
		}

		Map<String, Object> parm = new HashMap<String, Object>();

		List<MenuItemVo> resultList = new ArrayList<MenuItemVo>();
		MenuItemVo tempVo = null;
		for (MenuItemPo po : menuItems.values()) {
			tempVo = new MenuItemVo();
			tempVo.poToVo(po);
			resultList.add(tempVo);
		}

		parm.put("menuItemList", resultList);
		
		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);

		return rs;
	}

}
