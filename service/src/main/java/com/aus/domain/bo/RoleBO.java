package com.aus.domain.bo;

import java.util.Date;

/**
 * Created by xy on 2017/11/22.
 */
public class RoleBO {

    private Integer id;
    private String roleName;
    private String code;
    private String authorityCode;
    private Date createDate;

    public RoleBO() {
    }

    public RoleBO(Integer id) {
        this.id = id;
    }

    public RoleBO(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(String authorityCode) {
        this.authorityCode = authorityCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
