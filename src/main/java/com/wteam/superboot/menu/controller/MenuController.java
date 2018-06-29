/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wteam.superboot.core.entity.po.UserPo;
import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.menu.controller.Param.MenuParam;
import com.wteam.superboot.menu.service.MenuService;

/**
 * 菜单 Controller.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
@RestController
public class MenuController {

	/**
	 * 注入 service.
	 */
	@Autowired
	private MenuService service;

	/**
	 * 菜单分页.
	 * 
	 * @param param
	 *            请求数据.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/pageMenu")
	public ResultMessage pageMenu(@RequestBody MenuParam param) throws Exception {
		return service.pageMenu(param.getPageinfo(), param.getMenu());
	}

	/**
	 * 批量添加菜单.
	 * 
	 * @param param
	 *            请求数据.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/addMenuByList")
	public ResultMessage addMenuByList(@RequestBody MenuParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		return service.addMenuItemByList(param.getMenuList(), currentUser);
	}

	/**
	 * 批量编辑菜单.
	 * 
	 * @param param
	 *            请求数据.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/editMenuByList")
	public ResultMessage editMenuByList(@RequestBody MenuParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		return service.editMenuByList(param.getMenuList(), currentUser);
	}

	/**
	 * 批量删除菜单.
	 * 
	 * @param param
	 *            请求数据.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@PostMapping("/deleteMenuByList")
	public ResultMessage deleteMenuByList(@RequestBody MenuParam param,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		return service.deleteMenuByList(param.getMenuList(), currentUser);
	}

	/**
	 * 获取当前用户指定菜单.
	 * 
	 * @param menuname
	 *            菜单名.
	 * @param currentUser
	 *            当前用户.
	 * @return 结果集.
	 * @throws Exception
	 *             异常抛出.
	 */
	@GetMapping("/getCurrentUserMenuItemList")
	public ResultMessage getCurrentUserMenuItemList(String menuname,
			@RequestAttribute("currentUser") UserPo currentUser) throws Exception {
		return service.getCurrentUserMenuItemList(menuname, currentUser);
	}
}
