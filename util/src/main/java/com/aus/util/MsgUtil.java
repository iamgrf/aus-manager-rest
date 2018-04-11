package com.aus.util;

import java.util.HashMap;

/**
 * 组装客户端信息
 * Created by xy on 2017/5/11.
 */
public class MsgUtil {

    public static java.util.Map<String, Object> fail(){

        java.util.Map<String, Object> msgMap = new HashMap<>();
        msgMap.put("code", ErrorTypeEnum.FAIL.getCode());
        msgMap.put("msg", ErrorTypeEnum.FAIL.getMsg());
        return msgMap;

    }

    public static java.util.Map<String, Object> fail(ErrorTypeEnum error){

        java.util.Map<String, Object> msgMap = new HashMap<>();
        msgMap.put("code", error.getCode());
        msgMap.put("msg", error.getMsg());
        return msgMap;

    }

    public static java.util.Map<String, Object> fail(String msg){

        java.util.Map<String, Object> msgMap = new HashMap<>();
        msgMap.put("code", ErrorTypeEnum.FAIL.getCode());
        msgMap.put("msg", msg);
        return msgMap;

    }


    public static java.util.Map<String, Object> successful(){

        java.util.Map<String, Object> msgMap = new HashMap<>();
        msgMap.put("code", ErrorTypeEnum.SUCCESSFUL.getCode());
        msgMap.put("msg", ErrorTypeEnum.SUCCESSFUL.getMsg());
        return msgMap;

    }

    public static java.util.Map<String, Object> successful(Object data){

        java.util.Map<String, Object> msgMap = new HashMap<>();
        msgMap.put("code", ErrorTypeEnum.SUCCESSFUL.getCode());
        msgMap.put("msg", ErrorTypeEnum.SUCCESSFUL.getMsg());
        msgMap.put("data", data);
        return msgMap;

    }


}
