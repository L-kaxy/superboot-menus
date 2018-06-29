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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wteam.superboot.core.entity.po.PageinfoPo;
import com.wteam.superboot.core.entity.po.UserPo;
import com.wteam.superboot.core.enums.ResultEnum;
import com.wteam.superboot.core.exception.SuperException;
import com.wteam.superboot.core.helper.ResultHelper;
import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.menu.entity.po.MenuItemPo;
import com.wteam.superboot.menu.entity.po.MenuPo;
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
 * 菜单 Service类.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@Service
@Transactional
public class MenuService {

	/**
	 * 注入 Repository.
	 */
	@Autowired
	private MenuItemRepository menuItemRepository;
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private UserauthitemmapRepository userauthitemmapRepository;
	@Autowired
	private AuthitemmapRepository authitemmapRepository;
	@Autowired
	private ResourcetypeRepository resourcetypeRepository;
	@Autowired
	private PermissionresourcemapRepository permissionresourcemapRepository;
	@Autowired
	private ResourceRepository resourceRepository;

	/**
	 * 批量添加菜单.
	 * 
	 * @param list
	 *            菜单列表.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
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
	 * @param list
	 *            菜单列表.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
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
	 * @param list
	 *            菜单列表.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
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
	 * @param pageinfo
	 *            分页信息.
	 * @param likePo
	 *            模糊查询对象.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage pageMenu(final PageinfoPo pageinfo, final MenuPo likePo) throws Exception {
		if (pageinfo == null) {
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
		parm.put("pageList", pageResult.getContent());
		parm.put("totalCount", pageResult.getTotalElements());

		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);
		return rs;
	}

	/**
	 * 获取当前用户指定菜单.
	 * 
	 * @param menuname
	 *            菜单名.
	 * @param userPo
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	public ResultMessage getCurrentUserMenuItemList(final String menuname, final UserPo userPo) throws Exception {
		if (menuname == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}
		if (userPo == null) {
			throw new SuperException(ResultEnum.PARAM_ERROR);
		}

		MenuPo menuPo = new MenuPo();
		menuPo.setMenuname(menuname);

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
		parm.put("menuItemList", menuItems.values());

		ResultMessage rs = ResultHelper.result(ResultEnum.GET_SUCCESS, parm);
		return rs;
	}

}
