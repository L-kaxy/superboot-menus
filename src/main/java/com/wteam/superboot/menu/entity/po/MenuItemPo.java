/**
 * Copyright (c) 2017-2018 Tianxin.  All rights reserved. 广州天新网络科技有限公司 版权所有.
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
 * @author 罗佳欣
 * @version 1.2.0
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
	 * @return 设置 menuitemid 的值.
	 */
	public Long getMenuitemid() {
		return menuitemid;
	}

	/**
	 * 设置 menuitemid 的值.
	 * 
	 * @param menuitemid
	 *            赋值给 menuitemid.
	 */
	public void setMenuitemid(Long menuitemid) {
		this.menuitemid = menuitemid;
	}

	/**
	 * @return 设置 menuid 的值.
	 */
	public Long getMenuid() {
		return menuid;
	}

	/**
	 * 设置 menuid 的值.
	 * 
	 * @param menuid
	 *            赋值给 menuid.
	 */
	public void setMenuid(Long menuid) {
		this.menuid = menuid;
	}

	/**
	 * @return 设置 menuitemname 的值.
	 */
	public String getMenuitemname() {
		return menuitemname;
	}

	/**
	 * 设置 menuitemname 的值.
	 * 
	 * @param menuitemname
	 *            赋值给 menuitemname.
	 */
	public void setMenuitemname(String menuitemname) {
		this.menuitemname = menuitemname;
	}

	/**
	 * @return 设置 menuitemicon 的值.
	 */
	public String getMenuitemicon() {
		return menuitemicon;
	}

	/**
	 * 设置 menuitemicon 的值.
	 * 
	 * @param menuitemicon
	 *            赋值给 menuitemicon.
	 */
	public void setMenuitemicon(String menuitemicon) {
		this.menuitemicon = menuitemicon;
	}

	/**
	 * @return 设置 menuitemorder 的值.
	 */
	public Long getMenuitemorder() {
		return menuitemorder;
	}

	/**
	 * 设置 menuitemorder 的值.
	 * 
	 * @param menuitemorder
	 *            赋值给 menuitemorder.
	 */
	public void setMenuitemorder(Long menuitemorder) {
		this.menuitemorder = menuitemorder;
	}

	/**
	 * @return 设置 menuitemurl 的值.
	 */
	public String getMenuitemurl() {
		return menuitemurl;
	}

	/**
	 * 设置 menuitemurl 的值.
	 * 
	 * @param menuitemurl
	 *            赋值给 menuitemurl.
	 */
	public void setMenuitemurl(String menuitemurl) {
		this.menuitemurl = menuitemurl;
	}

	/**
	 * @return 设置 menuitemrouteurl 的值.
	 */
	public String getMenuitemrouteurl() {
		return menuitemrouteurl;
	}

	/**
	 * 设置 menuitemrouteurl 的值.
	 * 
	 * @param menuitemrouteurl
	 *            赋值给 menuitemrouteurl.
	 */
	public void setMenuitemrouteurl(String menuitemrouteurl) {
		this.menuitemrouteurl = menuitemrouteurl;
	}

	/**
	 * @return 设置 menuitemctrl 的值.
	 */
	public String getMenuitemctrl() {
		return menuitemctrl;
	}

	/**
	 * 设置 menuitemctrl 的值.
	 * 
	 * @param menuitemctrl
	 *            赋值给 menuitemctrl.
	 */
	public void setMenuitemctrl(String menuitemctrl) {
		this.menuitemctrl = menuitemctrl;
	}

	/**
	 * @return 设置 menuitemparentid 的值.
	 */
	public Long getMenuitemparentid() {
		return menuitemparentid;
	}

	/**
	 * 设置 menuitemparentid 的值.
	 * 
	 * @param menuitemparentid
	 *            赋值给 menuitemparentid.
	 */
	public void setMenuitemparentid(Long menuitemparentid) {
		this.menuitemparentid = menuitemparentid;
	}

	/**
	 * @return 设置 isshow 的值.
	 */
	public Boolean getIsshow() {
		return isshow;
	}

	/**
	 * 设置 isshow 的值.
	 * 
	 * @param isshow
	 *            赋值给 isshow.
	 */
	public void setIsshow(Boolean isshow) {
		this.isshow = isshow;
	}

}
