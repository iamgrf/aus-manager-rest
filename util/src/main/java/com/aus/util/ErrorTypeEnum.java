package com.aus.util;

/**
 * Created by xy on 2017/5/11.
 */
public enum ErrorTypeEnum {

    FAIL(0, "操作失败"),
    SUCCESSFUL(1, "操作成功"),
    ERROR_USER_PWD(10001, "用户名或密码有误"),
    ERROR_PARAM(10002, "参数有误"),
    ERROR_DISABLE(10003, "你已被禁用，请联系管理员"),
    ERROR_USER_EXIST(10004, "用户已存在"),
    ERROR_DATA_EXIST(10005, "数据已存在"),
    ERROR_DISACCESS(10006, "没有权限"),
    ERROR_RELOGIN(10007, "重新登录"),
    ERROR_DEPENDENT(10008, "数据被依赖"),
    ERROR_OLDPASSWORD(10009, "旧密码误"),
    ERROR_CEILING(10010, "已达上限");

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ErrorTypeEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
