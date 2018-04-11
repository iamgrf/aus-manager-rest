package com.aus.domain.bo;

import java.util.Date;

/**
 * Created by xy on 2017/11/20.
 */
public class UserBO {

    private Integer id;
    private RoleBO roleBO;
    private String realName;
    private String account;
    private String password;
    private String disable;
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public RoleBO getRoleBO() {
        return roleBO;
    }

    public void setRoleBO(RoleBO roleBO) {
        this.roleBO = roleBO;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }
}
