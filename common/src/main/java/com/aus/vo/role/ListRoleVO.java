package com.aus.vo.role;

import com.aus.vo.BaseVO;

/**
 * Created by xy on 2017/11/22.
 */
public class ListRoleVO extends BaseVO {

    private String roleName;
    private String code;

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
}
