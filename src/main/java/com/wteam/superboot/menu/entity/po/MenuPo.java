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
 * 菜单持久层类.
 * 
 */
@Entity
@Table(name = "t_menu")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MenuPo extends BasePersistentObject {

	/**
	 * 菜单编号.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long menuid;

	/**
	 * 菜单名.
	 */
	@Column(nullable = false)
	private String menuname;

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
	 * @return 获取的menuname
	 */
	public String getMenuname() {
		return menuname;
	}

	/**
	 * 设置menuname的方法.
	 * 
	 * @param menuname
	 *            赋值给menuname的值
	 */
	public void setMenuname(final String menuname) {
		this.menuname = menuname;
	}
}
