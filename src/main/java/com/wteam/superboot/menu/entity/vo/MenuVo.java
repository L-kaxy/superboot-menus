/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.menu.entity.vo;

import com.wteam.superboot.core.entity.vo.BaseValueObject;

/**
 * 菜单持久层类.
 * 
 */
public class MenuVo extends BaseValueObject {

    /**
     * 菜单编号.
     */
    private String menuid;

    /**
     * 菜单名.
     */
    private String menuname;

    /**
     * @return 获取的menuid
     */
    public final String getMenuid() {
        return menuid;
    }

    /**
     * 设置menuid的方法.
     * 
     * @param menuid
     *            赋值给menuid的值
     */
    public final void setMenuid(final String menuid) {
        this.menuid = menuid;
    }

    /**
     * @return 获取的menuname
     */
    public final String getMenuname() {
        return menuname;
    }

    /**
     * 设置menuname的方法.
     * 
     * @param menuname
     *            赋值给menuname的值
     */
    public final void setMenuname(final String menuname) {
        this.menuname = menuname;
    }

}
