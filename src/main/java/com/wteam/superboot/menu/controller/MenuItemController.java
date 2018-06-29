/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wteam.superboot.core.entity.po.UserPo;
import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.menu.controller.Param.MenuItemParam;
import com.wteam.superboot.menu.service.MenuItemService;

/**
 * 菜单项Controller.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@RestController
public class MenuItemController {

	@Autowired
	private MenuItemService service;

	@PostMapping("/getMenuItemByMenuItemId")
	public ResultMessage getMenuItemByMenuItemId(@RequestBody MenuItemParam param) throws Exception {
		return service.getMenuItemByMenuItemId(param.getMenuItem());
	}

	@PostMapping("/addMenuItem")
	public ResultMessage addMenuItem(@RequestBody MenuItemParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		return service.addMenuItem(param.getMenuItem(), param.getPermissionList(), currentUser);
	}

	@PostMapping("/editMenuItem")
	public ResultMessage editMenuItem(@RequestBody MenuItemParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		return service.editMenuItem(param.getMenuItem(), param.getPermissionList(), param.getPermissionList2(),
				currentUser);
	}

	@PostMapping("/editMenuItemByList")
	public ResultMessage editMenuItemByList(@RequestBody MenuItemParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		return service.editMenuItemByList(param.getMenuItemList(), currentUser);
	}

	@PostMapping("/deleteMenuItemByList")
	public ResultMessage deleteMenuItemByList(@RequestBody MenuItemParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		return service.deleteMenuItemByList(param.getMenuItemList(), currentUser);
	}

	@PostMapping("/getMenuMenuItemList")
	public ResultMessage getMenuMenuItemList(@RequestBody MenuItemParam param) throws Exception {
		return service.getMenuMenuItemList(param.getMenuItem());
	}
}
