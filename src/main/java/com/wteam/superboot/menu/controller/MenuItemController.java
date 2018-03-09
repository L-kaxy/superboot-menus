/**
 * Copyright (c) 2007-2017 Wteam.  All rights reserved. 网维网络技术创业团队 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.menu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wteam.superboot.core.entity.po.UserPo;
import com.wteam.superboot.core.helper.JsonHelper;
import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.menu.controller.Param.MenuParam;
import com.wteam.superboot.menu.entity.po.MenuItemPo;
import com.wteam.superboot.menu.service.MenuItemService;
import com.wteam.superboot.security.entity.po.AuthitemPo;

/**
 * 菜单项Controller.
 * 
 * @authod 罗佳欣
 * 
 */
@RestController
public class MenuItemController {

	@Autowired
	private MenuItemService service;

	@PostMapping("/getMenuItemByMenuItemId")
	public ResultMessage getMenuItemByMenuItemId(@RequestBody MenuParam param) throws Exception {
		MenuItemPo menuItemPo = param.getMenuItem().voToPo(MenuItemPo.class);
		return service.getMenuItemByMenuItemId(menuItemPo);
	}

	@PostMapping("/addMenuItem")
	public ResultMessage addMenuItem(@RequestBody MenuParam param, @RequestAttribute("currentUser") UserPo currentUser)
			throws Exception {
		List<AuthitemPo> addPermissionPos = JsonHelper.jsonToBeanList(param.getPermissionList(), AuthitemPo.class);
		MenuItemPo menuItemPo = param.getMenuItem().voToPo(MenuItemPo.class);
		return service.addMenuItem(menuItemPo, addPermissionPos, currentUser);
	}

	@PostMapping("/editMenuItem")
	public ResultMessage editMenuItem(@RequestBody MenuParam param, @RequestAttribute("currentUser") UserPo currentUser)
			throws Exception {
		List<AuthitemPo> addPermissionPos = JsonHelper.jsonToBeanList(param.getPermissionList(), AuthitemPo.class);
		List<AuthitemPo> subPermissionPos = JsonHelper.jsonToBeanList(param.getPermissionList2(), AuthitemPo.class);
		MenuItemPo menuItemPo = param.getMenuItem().voToPo(MenuItemPo.class);
		return service.editMenuItem(menuItemPo, addPermissionPos, subPermissionPos, currentUser);
	}

	@PostMapping("/editMenuItemByList")
	public ResultMessage editMenuItemByList(@RequestBody MenuParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		List<MenuItemPo> poList = JsonHelper.jsonToBeanList(param.getMenuItemList(), MenuItemPo.class);
		return service.editMenuItemByList(poList, currentUser);
	}

	@PostMapping("/deleteMenuItemByList")
	public ResultMessage deleteMenuItemByList(@RequestBody MenuParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		List<MenuItemPo> poList = JsonHelper.jsonToBeanList(param.getMenuItemList(), MenuItemPo.class);
		return service.deleteMenuItemByList(poList, currentUser);
	}

	@PostMapping("/getMenuMenuItemList")
	public ResultMessage getMenuMenuItemList(@RequestBody MenuParam param) throws Exception {
		MenuItemPo menuItemPo = param.getMenuItem().voToPo(MenuItemPo.class);
		if (menuItemPo == null) {
			menuItemPo = new MenuItemPo();
		}
		return service.getMenuMenuItemList(menuItemPo);
	}
}
