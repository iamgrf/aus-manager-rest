package com.aus.repository.dao.impl;

import com.aus.dao.MenuDao;
import com.aus.domain.bo.MenuBO;
import com.aus.repository.db.mybaits.MenuMapper;
import com.aus.repository.po.MenuPO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xy on 2017/11/20.
 */
@Repository
public class MenuDaoImpl implements MenuDao {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Integer add(MenuBO menuBO) {
        MenuPO menuPO = convert(menuBO);
        return menuMapper.insert(menuPO);
    }

    @Override
    public List<MenuBO> list(MenuBO menuBO) {
        MenuPO menuPO = convert(menuBO);
        List<MenuPO> menuPOs =  menuMapper.select(menuPO);

        List<MenuBO> menuBOs = new ArrayList<>(menuPOs.size());
        for (int i = 0; i < menuPOs.size(); i++) {
            menuBOs.add(convert(menuPOs.get(i)));
        }
        return menuBOs;
    }

    @Override
    public Integer delete(Integer id) {
        return menuMapper.delete(id);
    }

    @Override
    public Integer update(MenuBO menuBO) {
        MenuPO menuPO = convert(menuBO);
        return menuMapper.update(menuPO);
    }

    private MenuPO convert(MenuBO menuBO){
        MenuPO menuPO = new MenuPO();
        BeanUtils.copyProperties(menuBO, menuPO);
        if (menuBO.getParentMenuBO() != null){
            menuPO.setParentId(menuBO.getParentMenuBO().getId());
        }
        return menuPO;
    }

    private MenuBO convert(MenuPO menuPO){
        MenuBO menuBO = new MenuBO();
        BeanUtils.copyProperties(menuPO, menuBO);
        menuBO.setParentMenuBO(new MenuBO(menuPO.getParentId()));
        return menuBO;
    }
}
