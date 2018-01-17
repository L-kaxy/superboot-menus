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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wteam.superboot.core.entity.po.UserPo;
import com.wteam.superboot.core.enums.ResultEnum;
import com.wteam.superboot.core.exception.SuperException;
import com.wteam.superboot.core.helper.ResultHelper;
import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.menu.entity.po.MenuItemPo;
import com.wteam.superboot.menu.entity.vo.MenuItemVo;
import com.wteam.superboot.menu.repository.MenuItemRepository;
import com.wteam.superboot.security.entity.po.PermissionresourcemapPo;
import com.wteam.superboot.security.entity.po.ResourcePo;
import com.wteam.superboot.security.entity.po.ResourcetypePo;
import com.wteam.superboot.security.repository.PermissionresourcemapRepository;
import com.wteam.superboot.security.repository.ResourceRepository;
import com.wteam.superboot.security.repository.ResourcetypeRepository;

/**
 * 菜单项Service类.
 * 
 * @author 罗佳欣
 *
 */
@Service
@Transactional
public class MenuItemService {

	/**
	 * 注入menuItemRepository.
	 */
	@Autowired
	private MenuItemRepository menuItemRepository;

	/**
	 * 注入resourcetypeRepository.
	 */
	@Autowired
	private ResourcetypeRepository resourcetypeRepository;

	/**
	 * 注入resourceRepository.
	 */
	@Autowired
	private ResourceRepository resourceRepository;

	/**
	 * 注入permissionresourcemapRepository.
	 */
	@Autowired
	private PermissionresourcemapRepository permissionresourcemapRepository;

	/**
	 * 批量添加菜单项.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ResultMessage addMenuItemByList(final List<MenuItemPo> list, final UserPo currentUser) throws Exception {
		if (list == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (currentUser == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		ResourcetypePo type = new ResourcetypePo();
		type.setResourcetypename("menuitem");
		type = resourcetypeRepository.queryEntity(type);

		ResourcePo resource = null;
		for (MenuItemPo po : list) {
			menuItemRepository.addEntity(po, currentUser);
			resource = new ResourcePo();
			resource.setRealid(po.getMenuitemid());
			resource.setResourcetypeid(type.getResourcetypeid());
			resourceRepository.addEntity(resource, currentUser);
		}
		ResultMessage rs = ResultHelper.result(ResultEnum.ADD_SUCCESS);

		return rs;
	}

	/**
	 * 批量删除菜单项.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ResultMessage deleteMenuItemByList(final List<MenuItemPo> list, final UserPo currentUser) throws Exception {
		if (list == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (currentUser == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		ResourcetypePo type = new ResourcetypePo();
		type.setResourcetypename("menuitem");
		type = resourcetypeRepository.queryEntity(type);

		ResourcePo resource = null;
		PermissionresourcemapPo map = null;
		for (MenuItemPo po : list) {

			resource = new ResourcePo();
			resource.setRealid(po.getMenuitemid());
			resource.setResourcetypeid(type.getResourcetypeid());
			if (resourceRepository.hasNonDeleteEntity(resource)) {
				resource = resourceRepository.queryEntity(resource);

				map = new PermissionresourcemapPo();
				map.setResourceid(resource.getResourceid());

				for (PermissionresourcemapPo po2 : permissionresourcemapRepository.queryList(map)) {
					permissionresourcemapRepository.deleteEntity(po2);
				}
				resourceRepository.deleteEntity(resource);
			}
			menuItemRepository.deleteEntity(po);
		}
		ResultMessage rs = ResultHelper.result(ResultEnum.DEL_SUCCESS);

		return rs;
	}

	/**
	 * 批量编辑菜单项.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ResultMessage editMenuItemByList(final List<MenuItemPo> list, final UserPo currentUser) throws Exception {
		if (list == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (currentUser == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		for (MenuItemPo po : list) {
			menuItemRepository.editEntity(po, currentUser);
		}
		ResultMessage rs = ResultHelper.result(ResultEnum.EDIT_SUCCESS);

		return rs;
	}

	/**
	 * 根据菜单项编号获取菜单项.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ResultMessage getMenuItemByMenuItemId(final MenuItemPo menuItem) throws Exception {
		if (menuItem == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		MenuItemPo menuItemPo = menuItemRepository.getEntityById(MenuItemPo.class, menuItem.getMenuitemid());

		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("menuItem", menuItemPo);

		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);

		return rs;
	}

	/**
	 * 获取指定菜单所有菜单项.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ResultMessage getMenuMenuItemList(final MenuItemPo menuItem) throws Exception {
		if (menuItem == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		MenuItemPo theMenuItem = new MenuItemPo();
		theMenuItem.setMenuid(menuItem.getMenuid());
		List<MenuItemPo> poList = menuItemRepository.queryNonDeleteList(theMenuItem);

		Map<String, Object> parm = new HashMap<String, Object>();
		List<MenuItemVo> resultList = new ArrayList<MenuItemVo>();
		MenuItemVo tempVo = null;
		for (MenuItemPo po : poList) {
			tempVo = new MenuItemVo();
			tempVo.poToVo(po);
			resultList.add(tempVo);
		}

		parm.put("menuItemList", resultList);

		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);

		return rs;
	}

}
