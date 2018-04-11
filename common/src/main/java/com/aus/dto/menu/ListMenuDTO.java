package com.aus.dto.menu;

/**
 * Created by xy on 2017/11/21.
 */
public class ListMenuDTO {

    private Integer id;
    private String menuName;
    private String menuCode;
    private Integer pow;
    private String createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getPow() {
        return pow;
    }

    public void setPow(Integer pow) {
        this.pow = pow;
    }
}
