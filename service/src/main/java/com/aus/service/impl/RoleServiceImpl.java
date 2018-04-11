package com.aus.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.aus.domain.MenuDomain;
import com.aus.domain.RoleDomain;
import com.aus.domain.UserDomain;
import com.aus.domain.bo.RoleBO;
import com.aus.domain.bo.UserBO;
import com.aus.dto.role.ListRoleDTO;
import com.aus.service.RoleService;
import com.aus.util.*;
import com.aus.vo.menu.AuthorizedVO;
import com.aus.vo.role.AddRoleVO;
import com.aus.vo.role.ListRoleVO;
import com.aus.vo.role.UpdateRoleVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by xy on 2017/11/22.
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDomain roleDomain;

    @Autowired
    private MenuDomain menuDomain;

    @Autowired
    private UserDomain userDomain;

    @Override
    public Map<String, Object> listRole(ListRoleVO listRoleVO, PageUtil pageUtil) {
        RoleBO roleBO = new RoleBO();
        roleBO.setRoleName(listRoleVO.getRoleName());
        roleBO.setCode(listRoleVO.getCode());
        Page<?> page = PageHelper.startPage(pageUtil.getPage(), pageUtil.getSize());
        List<RoleBO> roleBOs = roleDomain.list(roleBO);
        List<ListRoleDTO> listMenuDTOs = new ArrayList<>(roleBOs.size());
        ListRoleDTO listRoleDTO = null;
        for (int i = 0; i < roleBOs.size(); i++) {
            listRoleDTO = new ListRoleDTO();
            listRoleDTO.setId(roleBOs.get(i).getId());
            listRoleDTO.setRoleName(roleBOs.get(i).getRoleName());
            listRoleDTO.setCode(roleBOs.get(i).getCode());
            listRoleDTO.setAuthorityCode(roleBOs.get(i).getAuthorityCode());
            listRoleDTO.setCreateDate(DateUtil.dateToStr(roleBOs.get(i).getCreateDate()));
            listMenuDTOs.add(listRoleDTO);
        }
        pageUtil.setTotal(page.getTotal());
        pageUtil.setData(listMenuDTOs);
        return MsgUtil.successful(pageUtil);
    }

    @Override
    public Map<String, Object> delRole(Integer id) {
        if (id == null){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_PARAM);
        }
        UserBO userBO = new UserBO();
        userBO.setRoleBO(new RoleBO(id));
        List<UserBO> userBOs = userDomain.list(userBO);
        if (userBOs.size() > 0){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_DEPENDENT);
        }
        Integer b = roleDomain.delete(id);
        if (b > 0){
            return MsgUtil.fail(ErrorTypeEnum.SUCCESSFUL);
        }
        return MsgUtil.fail(ErrorTypeEnum.FAIL);
    }

    @Override
    public Map<String, Object> addRole(AddRoleVO addRoleVO) {
        if (StringUtils.isAnyEmpty(addRoleVO.getRoleName(), addRoleVO.getCode())){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_PARAM);
        }
        RoleBO roleBO = roleDomain.getByCode(addRoleVO.getCode());
        if (roleBO != null){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_DATA_EXIST);
        }
        roleBO = new RoleBO();
        roleBO.setRoleName(addRoleVO.getRoleName());
        roleBO.setCode(addRoleVO.getCode());
        roleBO.setAuthorityCode("");
        roleBO.setCreateDate(new Date());
        Integer b = roleDomain.save(roleBO);
        if (b > 0){
            return MsgUtil.successful();
        }
        return MsgUtil.fail();
    }

    @Override
    public Map<String, Object> updateRole(UpdateRoleVO updateRoleVO) {
        if (updateRoleVO.getId() == null || StringUtils.isAnyEmpty(updateRoleVO.getRoleName(), updateRoleVO.getCode())){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_PARAM);
        }
        RoleBO roleBO = roleDomain.getByCode(updateRoleVO.getCode());
        if (roleBO != null){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_DATA_EXIST);
        }

        roleBO = new RoleBO();
        roleBO.setId(updateRoleVO.getId());
        roleBO.setRoleName(updateRoleVO.getRoleName());
        roleBO.setCode(updateRoleVO.getCode());
        Integer b = roleDomain.update(roleBO);
        if (b > 0){
            MenuPoolUtil.loginerInfo.clear();
            return MsgUtil.successful();
        }
        return MsgUtil.fail();
    }

    @Override
    public Map<String, Object> authorized(AuthorizedVO authorizedVO) {
        if (authorizedVO.getId() == null || authorizedVO.getAuthorityCode() == null){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_PARAM);
        }
        RoleBO roleBO = new RoleBO();
        roleBO.setId(authorizedVO.getId());
        roleBO.setAuthorityCode(AuthenticationUtil.orOperation(authorizedVO.getAuthorityCode().split(",")));
        Integer b = roleDomain.authorized(roleBO);
        if (b > 0){
            MenuPoolUtil.loginerInfo.clear();
            return MsgUtil.successful();
        }
        return MsgUtil.fail();
    }

    @Override
    public Map<String, Object> cascadeMenu(Integer id) {
        if (id == null){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_PARAM);
        }

        RoleBO roleBO = roleDomain.findById(id);

        Map<String, Object> cascadeMenu = new HashMap<>();
        JSONArray menu = menuDomain.cascadeMenu();
        List<Integer> checkIds = new ArrayList<>();
        filter(roleBO.getAuthorityCode(), checkIds, menu);
        cascadeMenu.put("menu", menu);
        cascadeMenu.put("check", checkIds);
        return MsgUtil.successful(cascadeMenu);
    }

    private void filter(String authorityCode, List<Integer> checkIds, JSONArray menu){
        for (int i = 0; i < menu.size(); i++) {
            if (AuthenticationUtil.authentication(authorityCode, menu.getJSONObject(i).getInteger("id"))){
                checkIds.add(menu.getJSONObject(i).getInteger("id"));
            }
            filter(authorityCode, checkIds, menu.getJSONObject(i).getJSONArray("children"));
        }
    }
}
