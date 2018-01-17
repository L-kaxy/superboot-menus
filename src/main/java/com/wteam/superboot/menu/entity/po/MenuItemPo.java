/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.menu.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.wteam.superboot.core.entity.po.BasePersistentObject;

/**
 * 菜单项持久层类.
 * 
 */
@Entity
@Table(name = "t_menuitem")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MenuItemPo extends BasePersistentObject {

	/**
	 * 菜单项编号.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long menuitemid;

	/**
	 * 菜单编号.
	 */
	@Column(nullable = false)
	private Long menuid;

	/**
	 * 菜单项标题.
	 */
	@Column(nullable = false)
	private String menuitemname;

	/**
	 * 菜单项图标.
	 */
	private String menuitemicon;

	/**
	 * 菜单项排序.
	 */
	private Long menuitemorder;

	/**
	 * 菜单项地址.
	 */
	private String menuitemurl;

	/**
	 * 菜单项路由地址.
	 */
	private String menuitemrouteurl;

	/**
	 * 菜单项控制器.
	 */
	private String menuitemctrl;

	/**
	 * 菜单项父菜单项编号.
	 */
	private Long menuitemparentid;

	/**
	 * 是否显示.
	 */
	private Boolean isshow;

	/**
	 * @return 获取的menuitemid
	 */
	public Long getMenuitemid() {
		return menuitemid;
	}

	/**
	 * 设置menuitemid的方法.
	 * 
	 * @param menuitemid
	 *            赋值给menuitemid的值
	 */
	public void setMenuitemid(final Long menuitemid) {
		this.menuitemid = menuitemid;
	}

	/**
	 * @return 获取的menuid
	 */
	public Long getMenuid() {
		return menuid;
	}

	/**
	 * 设置menuid的方法.
	 * 
	 * @param menuid
	 *            赋值给menuid的值
	 */
	public void setMenuid(Long menuid) {
		this.menuid = menuid;
	}

	/**
	 * @return 获取的menuitemname
	 */
	public String getMenuitemname() {
		return menuitemname;
	}

	/**
	 * 设置menuitemname的方法.
	 * 
	 * @param menuitemname
	 *            赋值给menuitemname的值
	 */
	public void setMenuitemname(final String menuitemname) {
		this.menuitemname = menuitemname;
	}

	/**
	 * @return 获取的menuitemicon
	 */
	public String getMenuitemicon() {
		return menuitemicon;
	}

	/**
	 * 设置menuitemicon的方法.
	 * 
	 * @param menuitemicon
	 *            赋值给menuitemicon的值
	 */
	public void setMenuitemicon(final String menuitemicon) {
		this.menuitemicon = menuitemicon;
	}

	/**
	 * @return 获取的menuitemorder
	 */
	public Long getMenuitemorder() {
		return menuitemorder;
	}

	/**
	 * 设置menuitemorder的方法.
	 * 
	 * @param menuitemorder
	 *            赋值给menuitemorder的值
	 */
	public void setMenuitemorder(final Long menuitemorder) {
		this.menuitemorder = menuitemorder;
	}

	/**
	 * @return 获取的menuitemurl
	 */
	public String getMenuitemurl() {
		return menuitemurl;
	}

	/**
	 * 设置menuitemurl的方法.
	 * 
	 * @param menuitemurl
	 *            赋值给menuitemurl的值
	 */
	public void setMenuitemurl(final String menuitemurl) {
		this.menuitemurl = menuitemurl;
	}

	/**
	 * @return 获取的menuitemrouteurl
	 */
	public String getMenuitemrouteurl() {
		return menuitemrouteurl;
	}

	/**
	 * 设置menuitemrouteurl的方法.
	 * 
	 * @param menuitemrouteurl
	 *            赋值给menuitemrouteurl的值
	 */
	public void setMenuitemrouteurl(final String menuitemrouteurl) {
		this.menuitemrouteurl = menuitemrouteurl;
	}

	/**
	 * @return 获取的menuitemctrl
	 */
	public String getMenuitemctrl() {
		return menuitemctrl;
	}

	/**
	 * 设置menuitemctrl的方法.
	 * 
	 * @param menuitemctrl
	 *            赋值给menuitemctrl的值
	 */
	public void setMenuitemctrl(final String menuitemctrl) {
		this.menuitemctrl = menuitemctrl;
	}

	/**
	 * @return 获取的menuitemparentid
	 */
	public Long getMenuitemparentid() {
		return menuitemparentid;
	}

	/**
	 * 设置menuitemparentid的方法.
	 * 
	 * @param menuitemparentid
	 *            赋值给menuitemparentid的值
	 */
	public void setMenuitemparentid(final Long menuitemparentid) {
		this.menuitemparentid = menuitemparentid;
	}

	/**
	 * @return 获取的isshow
	 */
	public Boolean getIsshow() {
		return isshow;
	}

	/**
	 * 设置isshow的方法.
	 * 
	 * @param isshow
	 *            赋值给isshow的值
	 */
	public void setIsshow(final Boolean isshow) {
		this.isshow = isshow;
	}
}
