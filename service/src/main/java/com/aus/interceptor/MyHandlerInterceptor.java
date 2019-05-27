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

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");

        String path = request.getServletPath();
        for (int i = 0; i < urls.length; i++) {
            if (path.startsWith(urls[i])){
                return true;
            }
        }
        if (path.endsWith(".html") || path.endsWith(".css") ||
                path.endsWith(".js") || path.endsWith(".png") || path.endsWith(".jpg")){
            return true;
        }
        String token = request.getHeader("Authorization");
        if (StringUtils.isNotEmpty(token)){
            try{
                String user = TokenUtil.verifyToken(token);
                JSONObject userJSON = JSON.parseObject(user);
                Integer pow = MenuPoolUtil.menus.get(path);
                if (pow == null){
                    return true;
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
        return out(response, ErrorTypeEnum.ERROR_RELOGIN);
    }

    private Boolean out(HttpServletResponse response, ErrorTypeEnum msg){
        response.setCharacterEncoding("utf-8");
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
