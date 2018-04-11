package com.aus.service;

import com.aus.util.PageUtil;
import com.aus.vo.user.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by xy on 2017/11/20.
 */
public interface UserService {
    Map<String,Object> login(LoginVO loginVO);

    Map<String,Object> listUser(ListUserVO listUserVO, PageUtil pageUtil);

    Map<String,Object> addUser(AddUserVO addUserVO);

    Map<String,Object> authorized(AuthorizedVO authorizedVO);

    Map<String,Object> updateUser(UpdateUserVO updateUserVO);

    Map<String,Object> delUser(String account);

    Map<String,Object> updatePassword(UpdatePasswordVO updatePasswordVO);
}
