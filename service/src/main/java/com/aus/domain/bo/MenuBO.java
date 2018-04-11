package com.aus.domain.bo;

import java.util.Date;

/**
 * Created by xy on 2017/11/20.
 */
public class MenuBO {

    private Integer id;
    private MenuBO parentMenuBO;
    private String menuName;
    private String menuCode;
    private Date createDate;

    public MenuBO() {
    }

    public MenuBO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MenuBO getParentMenuBO() {
        return parentMenuBO;
    }

    public void setParentMenuBO(MenuBO parentMenuBO) {
        this.parentMenuBO = parentMenuBO;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
