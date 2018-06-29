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
 * 菜单持久层类.
 * 
 * @author 罗佳欣
 * @version 1.2.0
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
	 * @return 设置 menuname 的值.
	 */
	public String getMenuname() {
		return menuname;
	}

	/**
	 * 设置 menuname 的值.
	 * 
	 * @param menuname
	 *            赋值给 menuname.
	 */
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

}
