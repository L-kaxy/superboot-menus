/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
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
import com.wteam.superboot.menu.repository.MenuItemRepository;
import com.wteam.superboot.security.entity.po.AuthitemPo;
import com.wteam.superboot.security.entity.po.PermissionresourcemapPo;
import com.wteam.superboot.security.entity.po.ResourcePo;
import com.wteam.superboot.security.entity.po.ResourcetypePo;
import com.wteam.superboot.security.repository.AuthitemRepository;
import com.wteam.superboot.security.repository.PermissionresourcemapRepository;
import com.wteam.superboot.security.repository.ResourceRepository;
import com.wteam.superboot.security.repository.ResourcetypeRepository;

/**
 * 菜单项 Service类.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@Service
@Transactional
public class MenuItemService {

	/**
	 * 注入 Repository.
	 */
	@Autowired
	private MenuItemRepository menuItemRepository;
	@Autowired
	private ResourcetypeRepository resourcetypeRepository;
	@Autowired
	private ResourceRepository resourceRepository;
	@Autowired
	private AuthitemRepository authitemRepository;
	@Autowired
	private PermissionresourcemapRepository permissionresourcemapRepository;

	/**
	 * 批量添加菜单项.
	 * 
	 * @param menuItem
	 *            菜单项实体.
	 * @param permissions
	 *            行为列表.
	 * @param currentUser
	 *            当期用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage addMenuItem(final MenuItemPo menuItem, final List<AuthitemPo> permissions,
			final UserPo currentUser) throws Exception {
		if (menuItem == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (currentUser == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		ResourcetypePo type = new ResourcetypePo();
		type.setResourcetypename("menuitem");
		type = resourcetypeRepository.queryEntity(type);

		menuItemRepository.addEntity(menuItem, currentUser);
		ResourcePo resource = new ResourcePo();
		resource.setRealid(menuItem.getMenuitemid());
		resource.setResourcetypeid(type.getResourcetypeid());
		resourceRepository.addEntity(resource, currentUser);

		PermissionresourcemapPo prmap = null;
		for (AuthitemPo tempPermission : permissions) {
			prmap = new PermissionresourcemapPo();
			prmap.setPermissionid(tempPermission.getAuthitemid());
			prmap.setResourceid(resource.getResourceid());
			permissionresourcemapRepository.addEntity(prmap, currentUser);
		}
		ResultMessage rs = ResultHelper.result(ResultEnum.ADD_SUCCESS);

		return rs;
	}

	/**
	 * 批量删除菜单项.
	 * 
	 * @param list
	 *            菜单项列表.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
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
	 * @param menuItem
	 *            菜单项.
	 * @param addPermissions
	 *            添加的行为列表.
	 * @param subPermissions
	 *            删除的行为列表.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage editMenuItem(final MenuItemPo menuItem, final List<AuthitemPo> addPermissions,
			final List<AuthitemPo> subPermissions, final UserPo currentUser) throws Exception {
		if (menuItem == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (currentUser == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		menuItemRepository.editEntity(menuItem, currentUser);

		ResourcetypePo type = new ResourcetypePo();
		type.setResourcetypename("menuitem");
		type = resourcetypeRepository.queryEntity(type);

		ResourcePo resource = new ResourcePo();
		resource.setResourcetypeid(type.getResourcetypeid());
		resource.setRealid(menuItem.getMenuitemid());
		resource = resourceRepository.queryEntity(resource);

		PermissionresourcemapPo prmapPo = null;
		for (AuthitemPo tempPermission : addPermissions) {
			prmapPo = new PermissionresourcemapPo();
			prmapPo.setResourceid(resource.getResourceid());
			prmapPo.setPermissionid(tempPermission.getAuthitemid());
			permissionresourcemapRepository.addEntity(prmapPo, currentUser);
		}
		for (AuthitemPo tempPermission : subPermissions) {
			prmapPo = new PermissionresourcemapPo();
			prmapPo.setResourceid(resource.getResourceid());
			prmapPo.setPermissionid(tempPermission.getAuthitemid());
			permissionresourcemapRepository.deleteEntity(prmapPo);
		}

		ResultMessage rs = ResultHelper.result(ResultEnum.EDIT_SUCCESS);

		return rs;
	}

	/**
	 * 批量编辑菜单项.
	 * 
	 * @param list
	 *            菜单项列表.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
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
	 * @param menuItem
	 *            菜单项.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage getMenuItemByMenuItemId(final MenuItemPo menuItem) throws Exception {
		if (menuItem == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		MenuItemPo menuItemPo = menuItemRepository.getEntityById(MenuItemPo.class, menuItem.getMenuitemid());

		ResourcetypePo type = new ResourcetypePo();
		type.setResourcetypename("menuitem");
		type = resourcetypeRepository.queryEntity(type);

		ResourcePo resource = new ResourcePo();
		resource.setResourcetypeid(type.getResourcetypeid());
		resource.setRealid(menuItem.getMenuitemid());
		resource = resourceRepository.queryEntity(resource);

		PermissionresourcemapPo prmapPo = new PermissionresourcemapPo();
		prmapPo.setResourceid(resource.getResourceid());
		AuthitemPo pPo = null;
		List<AuthitemPo> permissionList = new ArrayList<AuthitemPo>();
		for (PermissionresourcemapPo tampPmapPo : permissionresourcemapRepository.queryNonDeleteList(prmapPo)) {
			pPo = authitemRepository.getEntityById(AuthitemPo.class, tampPmapPo.getPermissionid());
			permissionList.add(pPo);
		}

		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("menuItem", menuItemPo);
		parm.put("permissionList", permissionList);

		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);

		return rs;
	}

	/**
	 * 获取指定菜单所有菜单项.
	 * 
	 * @param menuItem
	 *            菜单项.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage getMenuMenuItemList(final MenuItemPo menuItem) throws Exception {
		if (menuItem == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		MenuItemPo theMenuItem = new MenuItemPo();
		theMenuItem.setMenuid(menuItem.getMenuid());
		List<MenuItemPo> poList = menuItemRepository.queryNonDeleteList(theMenuItem);

		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("menuItemList", poList);

		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);
		return rs;
	}

}
