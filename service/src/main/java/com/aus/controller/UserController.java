package com.aus.controller;

import com.aus.service.UserService;
import com.aus.util.PageUtil;
import com.aus.Route;
import com.aus.vo.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by xy on 2017/11/20.
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = Route.LOGIN)
    public Map<String, Object> login(@RequestBody LoginVO loginVO){
        return userService.login(loginVO);
    }

    @RequestMapping(value = Route.LIST_USER)
    public Map<String, Object> listUser(@RequestBody ListUserVO listUserVO, PageUtil pageUtil){
        return userService.listUser(listUserVO, pageUtil);
    }

    @RequestMapping(value = Route.UPDATE_PASSWORD)
    public Map<String, Object> updatePassword(UpdatePasswordVO updatePasswordVO){
        return userService.updatePassword(updatePasswordVO);
    }

    @RequestMapping(value = Route.ADD_USER)
    public Map<String, Object> addUser(@RequestBody AddUserVO addUserVO){
        return userService.addUser(addUserVO);
    }

    @RequestMapping(value = Route.DEL_USER)
    public Map<String, Object> delUser(@PathVariable("account") String account){
        return userService.delUser(account);
    }

    @RequestMapping(value = Route.UPDATE_USER)
    public Map<String, Object> updateUser(UpdateUserVO updateUserVO){
        return userService.updateUser(updateUserVO);
    }

    @RequestMapping(value = Route.AUTHORIZED_USER)
    public Map<String, Object> authorized(@RequestBody AuthorizedVO authorizedVO){
        return userService.authorized(authorizedVO);
    }

}
