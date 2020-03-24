package com.aus.service;

import com.aus.util.PageUtil;
import com.aus.vo.menu.AuthorizedVO;
import com.aus.vo.role.AddRoleVO;
import com.aus.vo.role.ListRoleVO;
import com.aus.vo.role.UpdateRoleVO;

import java.util.Map;

/**
 * Created by xy on 2017/11/22.
 */
public interface RoleService {
    Map<String,Object> addRole(AddRoleVO addRoleVO);

    Map<String,Object> listRole(ListRoleVO listRoleVO);

    Map<String,Object> authorized(AuthorizedVO authorizedVO);

    Map<String,Object> updateRole(UpdateRoleVO updateRoleVO);

    Map<String,Object> cascadeMenu(Integer id);

    Map<String,Object> delRole(Integer id);
}
