package com.wteam.superboot.menu.controller.Param;

import com.wteam.superboot.core.controller.param.CoreParam;
import com.wteam.superboot.core.entity.po.PageinfoPo;
import com.wteam.superboot.menu.entity.vo.MenuItemVo;
import com.wteam.superboot.menu.entity.vo.MenuVo;

/**
 * 返回参数.
 * 
 * @author 罗佳欣
 *
 */
public class MenuParam extends CoreParam {

	/**
	 * 菜单列表.
	 */
	private String menuList;

	/**
	 * 菜单实体.
	 */
	private MenuVo menu;

	/**
	 * 菜单项列表.
	 */
	private String menuItemList;

	/**
	 * 菜单项实体.
	 */
	private MenuItemVo menuItem;

	/**
	 * 分页信息.
	 */
	private PageinfoPo pageinfo;

	private String permissionList;

	private String permissionList2;

	public String getMenuList() {
		return menuList;
	}

	public void setMenuList(String menuList) {
		this.menuList = menuList;
	}

	public MenuVo getMenu() {
		return menu;
	}

	public void setMenu(MenuVo menu) {
		this.menu = menu;
	}

	public String getMenuItemList() {
		return menuItemList;
	}

	public void setMenuItemList(String menuItemList) {
		this.menuItemList = menuItemList;
	}

	public MenuItemVo getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItemVo menuItem) {
		this.menuItem = menuItem;
	}

	public PageinfoPo getPageinfo() {
		return pageinfo;
	}

	public void setPageinfo(PageinfoPo pageinfo) {
		this.pageinfo = pageinfo;
	}

	public String getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(String permissionList) {
		this.permissionList = permissionList;
	}

	public String getPermissionList2() {
		return permissionList2;
	}

	public void setPermissionList2(String permissionList2) {
		this.permissionList2 = permissionList2;
	}

}
