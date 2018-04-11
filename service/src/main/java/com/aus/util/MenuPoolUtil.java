package com.aus.util;

import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * Created by xy on 2017/11/27.
 */
public class MenuPoolUtil {

    /**
     * 菜单列表
     */
    public static Map<String, Integer> menus = new RegexConcurrentHashMap();

    static class RegexConcurrentHashMap<K,V> extends ConcurrentHashMap{
        @Override
        public Object get(Object key) {
            Object v = super.get(key);
            if (v == null){
                if (key instanceof String){
                    Enumeration keys = this.keys();
                    while (keys.hasMoreElements()){
                        String k = String.valueOf(keys.nextElement());
                        if (Pattern.matches(k, String.valueOf(key))){
                            return super.get(k);
                        }
                    }
                }
            }
            return v;
        }
    }

    /**
     * 用户拥有的权限列表，角色缓存
     * 不用每次登录都查一次
     */
    public static Map<String, Object> loginerInfo = new ConcurrentHashMap<>();

}
