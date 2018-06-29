/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.menu.controller.Param;

import java.util.List;

import com.wteam.superboot.core.controller.param.CoreParam;
import com.wteam.superboot.menu.entity.po.MenuItemPo;
import com.wteam.superboot.security.entity.po.AuthitemPo;

/**
 * 返回参数.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
public class MenuItemParam extends CoreParam {

	private List<MenuItemPo> menuItemList;

	private MenuItemPo menuItem;

	private List<AuthitemPo> permissionList;

	private List<AuthitemPo> permissionList2;

	/**
	 * @return 设置 menuItemList 的值.
	 */
	public List<MenuItemPo> getMenuItemList() {
		return menuItemList;
	}

	/**
	 * 设置 menuItemList 的值.
	 * 
	 * @param menuItemList
	 *            赋值给 menuItemList.
	 */
	public void setMenuItemList(List<MenuItemPo> menuItemList) {
		this.menuItemList = menuItemList;
	}

	/**
	 * @return 设置 menuItem 的值.
	 */
	public MenuItemPo getMenuItem() {
		return menuItem;
	}

	/**
	 * 设置 menuItem 的值.
	 * 
	 * @param menuItem
	 *            赋值给 menuItem.
	 */
	public void setMenuItem(MenuItemPo menuItem) {
		this.menuItem = menuItem;
	}

	/**
	 * @return 设置 permissionList 的值.
	 */
	public List<AuthitemPo> getPermissionList() {
		return permissionList;
	}

	/**
	 * 设置 permissionList 的值.
	 * 
	 * @param permissionList
	 *            赋值给 permissionList.
	 */
	public void setPermissionList(List<AuthitemPo> permissionList) {
		this.permissionList = permissionList;
	}

	/**
	 * @return 设置 permissionList2 的值.
	 */
	public List<AuthitemPo> getPermissionList2() {
		return permissionList2;
	}

	/**
	 * 设置 permissionList2 的值.
	 * 
	 * @param permissionList2
	 *            赋值给 permissionList2.
	 */
	public void setPermissionList2(List<AuthitemPo> permissionList2) {
		this.permissionList2 = permissionList2;
	}

}
