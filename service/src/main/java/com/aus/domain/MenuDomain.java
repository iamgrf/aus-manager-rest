package com.aus.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.aus.dao.MenuDao;
import com.aus.domain.bo.MenuBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by xy on 2017/11/20.
 */

@Service
public class MenuDomain {

    @Autowired
    private MenuDao menuDao;

    public Integer add(MenuBO menuBO) {
        return menuDao.add(menuBO);
    }

    public List<MenuBO> list(MenuBO menuBO){
        return menuDao.list(menuBO);
    }

    public MenuBO get(Integer id){
        MenuBO menuBO = new MenuBO();
        menuBO.setId(id);
        List<MenuBO> menuBOs = list(menuBO);
        if (menuBOs.size() == 0){
            return null;
        }
        return menuBOs.get(0);
    }

    public Integer delete(Integer id) {
        return menuDao.delete(id);
    }

    public Integer update(MenuBO menuBO) {
        return menuDao.update(menuBO);
    }

    private JSONArray cascadeMenu(Integer parentId){
        MenuBO menuBO = new MenuBO();
        menuBO.setParentMenuBO(new MenuBO(parentId));
        List<MenuBO> menuBOs = this.list(menuBO);
        JSONArray menuBOsJson = JSON.parseArray(JSON.toJSONString(menuBOs));
        for (int i = 0; i < menuBOsJson.size(); i++) {
            JSONArray childMenuBOs = cascadeMenu(menuBOs.get(i).getId());
            menuBOsJson.getJSONObject(i).put("children", childMenuBOs);
            menuBOsJson.getJSONObject(i).put("expand", true);
        }
        return menuBOsJson;
    }

    public JSONArray cascadeMenu() {
        return cascadeMenu(-1);
    }
}
