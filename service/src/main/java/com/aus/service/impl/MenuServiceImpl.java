package com.aus.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.aus.domain.MenuDomain;
import com.aus.domain.bo.MenuBO;
import com.aus.dto.menu.ListMenuDTO;
import com.aus.service.MenuService;
import com.aus.util.*;
import com.aus.vo.menu.AddMenuVO;
import com.aus.vo.menu.ListMenuVO;
import com.aus.vo.menu.UpdateMenuVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Created by xy on 2017/11/20.
 */

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDomain menuDomain;

    @Override
    public Map<String, Object> listMenu(ListMenuVO listMenuVO, PageUtil pageUtil) {
        MenuBO menuBO = new MenuBO();
        menuBO.setParentMenuBO(new MenuBO(listMenuVO.getParentId()));
        Page<?> page = PageHelper.startPage(pageUtil.getPage(), pageUtil.getSize());
        List<MenuBO> menuBOs = menuDomain.list(menuBO);
        List<ListMenuDTO> listMenuDTOs = new ArrayList<>(menuBOs.size());
        ListMenuDTO listMenuDTO = null;
        for (int i = 0; i < menuBOs.size(); i++) {
            listMenuDTO = new ListMenuDTO();
            listMenuDTO.setId(menuBOs.get(i).getId());
            listMenuDTO.setMenuName(menuBOs.get(i).getMenuName());
            listMenuDTO.setMenuCode(menuBOs.get(i).getMenuCode());
            listMenuDTO.setCreateDate(DateUtil.dateToStr(menuBOs.get(i).getCreateDate()));
            listMenuDTOs.add(listMenuDTO);
        }
        pageUtil.setPages(page.getPages());
        pageUtil.setData(listMenuDTOs);
        return MsgUtil.successful(pageUtil);
    }

    @Override
    public Map<String, Object> delMenu(Integer id) {
        if (id == null){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_PARAM);
        }
        MenuBO menuBO = new MenuBO();
        menuBO.setParentMenuBO(new MenuBO(id));
        List<MenuBO> menuBOs = menuDomain.list(menuBO);
        if(menuBOs.size() > 0){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_DEPENDENT);
        }
        Integer b = menuDomain.delete(id);
        if (b > 0){
            reloadMenus();
            return MsgUtil.successful();
        }
        return MsgUtil.fail();
    }

    @Override
    public Map<String, Object> updateMenu(UpdateMenuVO updateMenuVO) {
        if (updateMenuVO.getId() == null || StringUtils.isAnyEmpty(updateMenuVO.getMenuName(), updateMenuVO.getMenuCode())){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_PARAM);
        }

        MenuBO menuBO = new MenuBO();
        menuBO.setMenuName(updateMenuVO.getMenuName());
        List<MenuBO> menuBos = menuDomain.list(menuBO);
        if (menuBos.size() > 0){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_DATA_EXIST);
        }

        menuBO = new MenuBO();
        menuBO.setMenuCode(updateMenuVO.getMenuCode());
        menuBos = menuDomain.list(menuBO);
        if (menuBos.size() > 0){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_DATA_EXIST);
        }

        menuBO = new MenuBO();
        menuBO.setId(updateMenuVO.getId());
        menuBO.setMenuName(updateMenuVO.getMenuName());
        menuBO.setMenuCode(updateMenuVO.getMenuCode());
        Integer b = menuDomain.update(menuBO);
        if (b > 0){
            reloadMenus();
            return MsgUtil.successful();
        }
        return MsgUtil.fail();
    }

    @Override
    public Map<String, Object> cascadeMenu() {
        JSONArray menus = menuDomain.cascadeMenu();
        String menusStr = JSON.toJSONString(menus).replace("menuName", "title");
        return MsgUtil.successful(JSON.parseArray(menusStr));
    }

    @Override
    public Map<String, Object> addMenu(AddMenuVO addMenuVO) {
        if (addMenuVO.getParentId() == null || StringUtils.isAnyEmpty(addMenuVO.getMenuName(), addMenuVO.getMenuCode())){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_PARAM);
        }

        MenuBO menuBO = new MenuBO();
        menuBO.setMenuCode(addMenuVO.getMenuCode());
        List<MenuBO> menuBos = menuDomain.list(menuBO);
        if (menuBos.size() > 0){
            return MsgUtil.fail(ErrorTypeEnum.ERROR_DATA_EXIST);
        }

        menuBO.setParentMenuBO(new MenuBO(addMenuVO.getParentId()));
        menuBO.setMenuName(addMenuVO.getMenuName());
        menuBO.setMenuCode(addMenuVO.getMenuCode());
        menuBO.setCreateDate(new Date());
        Integer b = menuDomain.add(menuBO);
        if (b > 0){
            reloadMenus();
            return MsgUtil.successful();
        }
        return MsgUtil.fail();
    }

    private void reloadMenus(){
        MenuPoolUtil.menus.clear();
        MenuPoolUtil.loginerInfo.clear();
        //初始化全局菜单
        if (MenuPoolUtil.menus.size() == 0){
            List<MenuBO> menuBOs = menuDomain.list(new MenuBO());
            for (int i = 0; i < menuBOs.size(); i++) {
                MenuPoolUtil.menus.put(menuBOs.get(i).getMenuCode(), menuBOs.get(i).getId());
            }
        }
    }
}
