/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.menu.entity.vo;

import com.wteam.superboot.core.entity.vo.BaseValueObject;

/**
 * 菜单项数据值对象类.
 * 
 */
public class MenuItemVo extends BaseValueObject {

    /**
     * 菜单项编号.
     */
    private String menuitemid;

    /**
     * 菜单编号.
     */
    private String menuid;

    /**
     * 菜单项标题.
     */
    private String menuitemname;

    /**
     * 菜单项图标.
     */
    private String menuitemicon;

    /**
     * 菜单项排序.
     */
    private String menuitemorder;

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
    private String menuitemparentid;

    /**
     * 是否显示.
     */
    private String isshow;

    /**
     * @return 获取的menuitemid
     */
    public final String getMenuitemid() {
        return menuitemid;
    }

    /**
     * 设置menuitemid的方法.
     * 
     * @param menuitemid
     *            赋值给menuitemid的值
     */
    public final void setMenuitemid(final String menuitemid) {
        this.menuitemid = menuitemid;
    }

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
     * @return 获取的menuitemname
     */
    public final String getMenuitemname() {
        return menuitemname;
    }

    /**
     * 设置menuitemname的方法.
     * 
     * @param menuitemname
     *            赋值给menuitemname的值
     */
    public final void setMenuitemname(final String menuitemname) {
        this.menuitemname = menuitemname;
    }

    /**
     * @return 获取的menuitemicon
     */
    public final String getMenuitemicon() {
        return menuitemicon;
    }

    /**
     * 设置menuitemicon的方法.
     * 
     * @param menuitemicon
     *            赋值给menuitemicon的值
     */
    public final void setMenuitemicon(final String menuitemicon) {
        this.menuitemicon = menuitemicon;
    }

    /**
     * @return 获取的menuitemorder
     */
    public final String getMenuitemorder() {
        return menuitemorder;
    }

    /**
     * 设置menuitemorder的方法.
     * 
     * @param menuitemorder
     *            赋值给menuitemorder的值
     */
    public final void setMenuitemorder(final String menuitemorder) {
        this.menuitemorder = menuitemorder;
    }

    /**
     * @return 获取的menuitemurl
     */
    public final String getMenuitemurl() {
        return menuitemurl;
    }

    /**
     * 设置menuitemurl的方法.
     * 
     * @param menuitemurl
     *            赋值给menuitemurl的值
     */
    public final void setMenuitemurl(final String menuitemurl) {
        this.menuitemurl = menuitemurl;
    }

    /**
     * @return 获取的menuitemrouteurl
     */
    public final String getMenuitemrouteurl() {
        return menuitemrouteurl;
    }

    /**
     * 设置menuitemrouteurl的方法.
     * 
     * @param menuitemrouteurl
     *            赋值给menuitemrouteurl的值
     */
    public final void setMenuitemrouteurl(final String menuitemrouteurl) {
        this.menuitemrouteurl = menuitemrouteurl;
    }

    /**
     * @return 获取的menuitemctrl
     */
    public final String getMenuitemctrl() {
        return menuitemctrl;
    }

    /**
     * 设置menuitemctrl的方法.
     * 
     * @param menuitemctrl
     *            赋值给menuitemctrl的值
     */
    public final void setMenuitemctrl(final String menuitemctrl) {
        this.menuitemctrl = menuitemctrl;
    }

    /**
     * @return 获取的menuitemparentid
     */
    public final String getMenuitemparentid() {
        return menuitemparentid;
    }

    /**
     * 设置menuitemparentid的方法.
     * 
     * @param menuitemparentid
     *            赋值给menuitemparentid的值
     */
    public final void setMenuitemparentid(final String menuitemparentid) {
        this.menuitemparentid = menuitemparentid;
    }

    /**
     * @return 获取的isshow
     */
    public final String getIsshow() {
        return isshow;
    }

    /**
     * 设置isshow的方法.
     * 
     * @param isshow
     *            赋值给isshow的值
     */
    public final void setIsshow(final String isshow) {
        this.isshow = isshow;
    }

}
