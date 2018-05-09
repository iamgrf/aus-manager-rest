package com.aus.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aus.service.UserService;
import com.aus.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by xy on 2017/7/17.
 */
public class MyHandlerInterceptor implements HandlerInterceptor {

    private static String[] urls = null;
    static {
        urls = new String[]{
                "/user/login",
        };
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8010");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        response.setHeader("Access-Control-Allow-Credentials","true");

        String path = request.getServletPath();
        for (int i = 0; i < urls.length; i++) {
            if (path.startsWith(urls[i])){
                return true;
            }
        }
        if (path.endsWith(".html") || path.endsWith(".css") || path.endsWith(".js")){
            return true;
        }
        String token = request.getParameter("token");
        if (StringUtils.isNotEmpty(token)){
            try{
                String user = TokenUtil.verifyToken(token);
                JSONObject userJSON = JSON.parseObject(user);
                Integer pow = MenuPoolUtil.menus.get(path);
                if (pow == null){
                    return out(response, ErrorTypeEnum.ERROR_DISACCESS);
                }
                Boolean b = AuthenticationUtil.authentication(userJSON.getString("authorityCode"), pow);
                if (b){
                    return true;
                }else{
                    return out(response, ErrorTypeEnum.ERROR_DISACCESS);
                }
            }catch (Exception e){
                return out(response, ErrorTypeEnum.ERROR_RELOGIN);
            }
        }
        return false;
    }

    private Boolean out(HttpServletResponse response, ErrorTypeEnum msg){
        PrintWriter print = null;
        try{
            print = response.getWriter();
            print.print(JSON.toJSONString(MsgUtil.fail(msg)));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            print.close();
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
