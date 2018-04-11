package com.aus;

/**
 * Created by xy on 2017/7/6.
 */
public class Route {

    /**
     * 用户模块
     */
    private final static String USER = "user";

     /**
     * 登录
     */
    public final static String LOGIN = USER + "/login";

    /**
     * 用户列表
     */
    public final static String LIST_USER = USER + "/listUser";

    /**
     * 修改
     */
    public final static String UPDATE_PASSWORD = USER + "/updatePassword";

    /**
     * 添加用户
     */
    public final static String ADD_USER = USER + "/addUser";

    /**
     * 删除用户
     */
    public final static String DEL_USER = USER + "/delUser/{account}";

    /**
     * 修改用户
     */
    public final static String UPDATE_USER = USER + "/updateUser";

    /**
     * 授权给用户
     */
    public final static String AUTHORIZED_USER = USER + "/authorized";

    /**
     * 用户拥有菜单列表
     */
    public final static String MENUS_USER = USER + "/menus";

    /**
     * 鉴权
     */
    public final static String AUTHENTICATION_USER = USER + "/authentication";

    //==============================================可爱的分隔线======================================================

    /**
     * 角色模块
     */
    private final static String ROLE = "role";

    /**
     * 角色列表
     */
    public final static String LIST_ROLE = ROLE + "/listRole";

    /**
     * 添加角色
     */
    public final static String ADD_ROLE = ROLE + "/addRole";

    /**
     * 修改角色
     */
    public final static String UPDATE_ROLE = ROLE + "/updateRole";

    /**
     * 授权角色
     */
    public final static String AUTHORIZED_ROLE = ROLE + "/authorized";

    /**
     * 删除角色
     */
    public final static String DEL_ROLE = ROLE + "/delRole/{id}";

    /**
     * 角色查看菜单列表
     */
    public final static String CASCADEMENU_ROLE = ROLE + "/cascadeMenu/{id}";

    //==============================================可爱的分隔线======================================================

    /**
     * 菜单模块
     */
    private final static String MENU = "menu";

    /**
     * 菜单列表
     */
    public final static String LIST_MENU = MENU + "/listMenu";

    /**
     * 添加菜单
     */
    public final static String ADD_MENU = MENU + "/addMenu";

    /**
     * 删除菜单
     */
    public final static String DEL_MENU = MENU + "/delMenu/{id}";

    /**
     * 修改菜单
     */
    public final static String UPDATE_MENU = MENU + "/updateMenu";

    /**
     * 级联
     */
    public final static String CASCADE_MENU = MENU + "/cascadeMenu";
}
