package com.aus.vo.user;

/**
 * Created by xy on 2017/11/24.
 */
public class UpdateUserVO {

    private Integer id;
    private String realName;
    private String account;

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
}
