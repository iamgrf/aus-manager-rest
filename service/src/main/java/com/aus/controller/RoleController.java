package com.aus.controller;

import com.aus.service.RoleService;
import com.aus.util.PageUtil;
import com.aus.Route;
import com.aus.vo.menu.AuthorizedVO;
import com.aus.vo.role.AddRoleVO;
import com.aus.vo.role.ListRoleVO;
import com.aus.vo.role.UpdateRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by xy on 2017/11/22.
 */

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = Route.ADD_ROLE)
    public Map<String, Object> addRole(AddRoleVO addRoleVO){
        return roleService.addRole(addRoleVO);
    }

    @RequestMapping(value = Route.UPDATE_ROLE)
    public Map<String, Object> updateRole(UpdateRoleVO updateRoleVO){
        return roleService.updateRole(updateRoleVO);
    }

    @RequestMapping(value = Route.LIST_ROLE)
    public Map<String, Object> listRole(ListRoleVO listRoleVO, PageUtil pageUtil){
        return roleService.listRole(listRoleVO, pageUtil);
    }

    @RequestMapping(value = Route.AUTHORIZED_ROLE)
    public Map<String, Object> authorized(AuthorizedVO authorizedVO){
        return roleService.authorized(authorizedVO);
    }

    @RequestMapping(value = Route.DEL_ROLE)
    public Map<String, Object> delRole(@PathVariable("id") Integer id){
        return roleService.delRole(id);
    }

    /**
     * 菜单列表并返回当前菜单拥有的菜单id
     * @return
     */
    @RequestMapping(value = Route.CASCADEMENU_ROLE)
    public Map<String, Object> cascadeMenu(@PathVariable("id") Integer id){
        return roleService.cascadeMenu(id);
    }

}
