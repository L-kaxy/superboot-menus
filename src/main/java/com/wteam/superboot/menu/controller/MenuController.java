/**
 * Copyright (c) 2007-2017 Wteam.  All rights reserved. 网维网络技术创业团队 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.menu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wteam.superboot.core.entity.po.UserPo;
import com.wteam.superboot.core.helper.JsonHelper;
import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.menu.controller.Param.MenuParam;
import com.wteam.superboot.menu.entity.po.MenuPo;
import com.wteam.superboot.menu.service.MenuService;

/**
 * 菜单Controller.
 * 
 * @authod 罗佳欣
 * 
 */
@RestController
public class MenuController {

	@Autowired
	private MenuService service;

	@PostMapping("/pageMenu")
	public ResultMessage pageMenu(@RequestBody MenuParam param) throws Exception {
		MenuPo likePo = param.getMenu().voToPo(MenuPo.class);
		return service.pageMenu(param.getPageinfo(), likePo);
	}

	@PostMapping("/addMenuByList")
	public ResultMessage addMenuByList(@RequestBody MenuParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		List<MenuPo> poList = JsonHelper.jsonToBeanList(param.getMenuList(), MenuPo.class);
		return service.addMenuItemByList(poList, currentUser);
	}

	@PostMapping("/editMenuByList")
	public ResultMessage editMenuByList(@RequestBody MenuParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		List<MenuPo> poList = JsonHelper.jsonToBeanList(param.getMenuList(), MenuPo.class);
		return service.editMenuByList(poList, currentUser);
	}

	@PostMapping("/deleteMenuByList")
	public ResultMessage deleteMenuByList(@RequestBody MenuParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		List<MenuPo> poList = JsonHelper.jsonToBeanList(param.getMenuList(), MenuPo.class);
		return service.deleteMenuByList(poList, currentUser);
	}

	@GetMapping("/getCurrentUserMenuItemList")
	public ResultMessage getCurrentUserMenuItemList(String menuname,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		MenuPo menuPo = new MenuPo();
		menuPo.setMenuname(menuname);
		return service.getCurrentUserMenuItemList(menuPo, currentUser);
	}
}
