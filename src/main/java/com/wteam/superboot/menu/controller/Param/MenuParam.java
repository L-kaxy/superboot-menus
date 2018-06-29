/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.menu.controller.Param;

import java.util.List;

import com.wteam.superboot.core.controller.param.CoreParam;
import com.wteam.superboot.core.entity.po.PageinfoPo;
import com.wteam.superboot.menu.entity.po.MenuPo;

/**
 * 返回参数.
 * 
 * @author 罗佳欣
 * @version 1.2.0
 */
public class MenuParam extends CoreParam {

	private List<MenuPo> menuList;

	private MenuPo menu;

	private PageinfoPo pageinfo;

	/**
	 * @return 设置 menuList 的值.
	 */
	public List<MenuPo> getMenuList() {
		return menuList;
	}

	/**
	 * 设置 menuList 的值.
	 * 
	 * @param menuList
	 *            赋值给 menuList.
	 */
	public void setMenuList(List<MenuPo> menuList) {
		this.menuList = menuList;
	}

	/**
	 * @return 设置 menu 的值.
	 */
	public MenuPo getMenu() {
		return menu;
	}

	/**
	 * 设置 menu 的值.
	 * 
	 * @param menu
	 *            赋值给 menu.
	 */
	public void setMenu(MenuPo menu) {
		this.menu = menu;
	}

	/**
	 * @return 设置 pageinfo 的值.
	 */
	public PageinfoPo getPageinfo() {
		return pageinfo;
	}

	/**
	 * 设置 pageinfo 的值.
	 * 
	 * @param pageinfo
	 *            赋值给 pageinfo.
	 */
	public void setPageinfo(PageinfoPo pageinfo) {
		this.pageinfo = pageinfo;
	}

}
