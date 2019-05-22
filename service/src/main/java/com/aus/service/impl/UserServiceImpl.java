package com.aus.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aus.domain.MenuDomain;
import com.aus.domain.RoleDomain;
import com.aus.domain.UserDomain;
import com.aus.domain.bo.MenuBO;
import com.aus.domain.bo.RoleBO;
import com.aus.domain.bo.UserBO;
import com.aus.dto.user.ListUserDTO;
import com.aus.service.UserService;
import com.aus.util.*;
import com.aus.vo.user.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by xy on 2017/11/20.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDomain userDomain;

    @Autowired
    private MenuDomain menuDomain;

    @Autowired
    private RoleDomain roleDomain;

    @Override
    public Map<String, Object> login(LoginVO loginVO) {
        if (StringUtils.isAnyEmpty(loginVO.getAccount(), loginVO.getPassword())){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_PARAM);
        }
        UserBO userBO = userDomain.findByAccount(loginVO.getAccount());
        if (userBO == null){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_USER_PWD);
        }
        if ("0".equals(userBO.getDisable())){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_DISABLE);
        }
        if (!PasswordUtil.verify( loginVO.getPassword() + loginVO.getAccount(), userBO.getPassword())){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_USER_PWD);
        }

        if (userBO.getRoleBO() == null || userBO.getRoleBO().getAuthorityCode() == null){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_DISACCESS);
        }

        ImmutableMap<String, String> userMap = ImmutableMap.of("account", userBO.getAccount(), "authorityCode", userBO.getRoleBO() == null ? "" : userBO.getRoleBO().getAuthorityCode());
        Map<String, Object> menus = this.menus(loginVO.getAccount());
        ImmutableMap<String, Object> v = ImmutableMap.of("token", TokenUtil.creataToken(JSON.toJSONString(userMap)), "realName", userBO.getRealName(), "role", menus);

        //初始化全局菜单
        if (MenuPoolUtil.menus.size() == 0){
            List<MenuBO> menuBOs = menuDomain.list(new MenuBO());
            for (int i = 0; i < menuBOs.size(); i++) {
                MenuPoolUtil.menus.put(menuBOs.get(i).getMenuCode(), menuBOs.get(i).getId());
            }
        }

        return MsgUtil.successful(v);
    }

    @Override
    public Map<String, Object> updatePassword(UpdatePasswordVO updatePasswordVO) {

        if (StringUtils.isAnyEmpty(updatePasswordVO.getOldPassword(), updatePasswordVO.getNewPassword())){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_PARAM);
        }

        String user = TokenUtil.verifyToken(updatePasswordVO.getToken());
        JSONObject userJSON = JSON.parseObject(user);
        String account = userJSON.getString("account");
        UserBO userBO = userDomain.findByAccount(account);

        String password = PasswordUtil.generatePassword(updatePasswordVO.getOldPassword());
        if (!password.equals(userBO.getPassword())){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_OLDPASSWORD);
        }
        password = PasswordUtil.generatePassword(updatePasswordVO.getNewPassword());
        userBO = new UserBO();
        userBO.setAccount(account);
        userBO.setPassword(password);
        Integer b = userDomain.update(userBO);
        if (b > 0){
            return MsgUtil.successful();
        }
        return MsgUtil.fail();
    }

    @Override
    public Map<String, Object> listUser(ListUserVO listUserVO, PageUtil pageUtil) {
        UserBO userBO = new UserBO();
        Page<?> page = PageHelper.startPage(pageUtil.getPage(), pageUtil.getSize());
        List<UserBO> userBOs = userDomain.list(userBO);

        List<ListUserDTO> listUserDTOs = new ArrayList<>(userBOs.size());
        ListUserDTO listUserDTO = null;
        for (int i = 0; i < userBOs.size(); i++) {
            listUserDTO = new ListUserDTO();
            listUserDTO.setId(userBOs.get(i).getId());
            listUserDTO.setRoleId(userBOs.get(i).getRoleBO().getId());
            listUserDTO.setRoleName(userBOs.get(i).getRoleBO().getRoleName());
            listUserDTO.setAccount(userBOs.get(i).getAccount());
            listUserDTO.setRealName(userBOs.get(i).getRealName());
            listUserDTO.setCreateDate(DateUtil.dateToStr(userBOs.get(i).getCreateDate()));
            listUserDTOs.add(listUserDTO);
        }
        pageUtil.setTotal(page.getTotal());
        pageUtil.setData(listUserDTOs);
        return MsgUtil.successful(pageUtil);
    }

    @Override
    public Map<String, Object> addUser(AddUserVO addUserVO) {

        if (StringUtils.isAnyEmpty(addUserVO.getAccount(), addUserVO.getRealName())){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_PARAM);
        }

        UserBO userBO = userDomain.findByAccount(addUserVO.getAccount());
        if (userBO != null){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_DATA_EXIST);
        }

        userBO = new UserBO();
        BeanUtils.copyProperties(addUserVO, userBO);
        userBO.setPassword(PasswordUtil.generatePassword(addUserVO.getPassword() + addUserVO.getAccount()));
        userBO.setDisable("1");
        userBO.setCreateDate(new Date());

        Integer b = userDomain.save(userBO);
        if (b > 0){
            return MsgUtil.successful();
        }
        return MsgUtil.fail();
    }

    @Override
    public Map<String, Object> updateUser(UpdateUserVO updateUserVO) {
        if (StringUtils.isAnyEmpty(updateUserVO.getAccount(), updateUserVO.getRealName())){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_PARAM);
        }



        UserBO userBO = new UserBO();
        userBO.setAccount(updateUserVO.getAccount());
        userBO.setRealName(updateUserVO.getRealName());

        Integer b = userDomain.update(userBO);
        if (b > 0){
            return MsgUtil.successful();
        }
        return MsgUtil.fail();
    }

    @Override
    public Map<String, Object> authorized(AuthorizedVO authorizedVO) {
        if (authorizedVO.getId() == null || authorizedVO.getRoleId() == null){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_PARAM);
        }
        UserBO userBO = new UserBO();
        userBO.setId(authorizedVO.getId());
        userBO.setRoleBO(new RoleBO(authorizedVO.getRoleId()));
        Integer b = userDomain.authorized(userBO);
        if (b > 0){
            MenuPoolUtil.loginerInfo.remove(userDomain.get(authorizedVO.getId()).getAccount());
            return MsgUtil.successful();
        }
        return MsgUtil.fail();
    }

    /**
     * 取得用户拥有的菜单
     * @param account
     * @return
     */
    private Map<String, Object> menus(String account) {
        if (StringUtils.isEmpty(account)){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_PARAM);
        }
        UserBO userBO = userDomain.findByAccount(account);
        if (userBO == null){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_PARAM);
        }

        if (MenuPoolUtil.loginerInfo.containsKey(account)){
            return ((Map<String, Object>) MenuPoolUtil.loginerInfo.get(account));
        }

        RoleBO roleBO = roleDomain.findById(userBO.getRoleBO().getId());

        JSONArray oneJsonArray = menuDomain.cascadeMenu();

        List<Integer> ids = new ArrayList<>();
        recursive(ids, roleBO.getAuthorityCode(), oneJsonArray);

        MenuBO menuBO = null;
        Map<String, Object> menusMap = new HashMap<>();
        menusMap.put("role_code", roleBO.getCode());
        menusMap.put("role_name", roleBO.getRoleName());
        List<Map<String, String>> menus = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            menuBO = menuDomain.get(ids.get(i));
            Map<String, String> map = new HashMap<>();
            map.put("menuName", menuBO.getMenuName());
            map.put("menuCode", menuBO.getMenuCode());
            menus.add(map);
        }
        menusMap.put("menus", menus);

        MenuPoolUtil.loginerInfo.put(account, menusMap);

        return menusMap;
    }

    private void recursive(List<Integer> ids, String authorityCode, JSONArray jsonArray){
        for (int i = 0; i < jsonArray.size(); i++) {
            if (AuthenticationUtil.authentication(authorityCode, jsonArray.getJSONObject(i).getInteger("id"))) {
                ids.add(jsonArray.getJSONObject(i).getInteger("id"));
            }
            recursive(ids, authorityCode, jsonArray.getJSONObject(i).getJSONArray("children"));
        }
    }

    @Override
    public Map<String, Object> delUser(String account) {
        if (StringUtils.isEmpty(account)){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_PARAM);
        }
        Integer b = userDomain.del(account);
        if (b > 0){
            return MsgUtil.successful();
        }
        return MsgUtil.fail();
    }

}
