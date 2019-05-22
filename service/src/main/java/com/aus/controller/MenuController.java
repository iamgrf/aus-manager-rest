package com.aus.controller;

import com.aus.service.MenuService;
import com.aus.util.PageUtil;
import com.aus.Route;
import com.aus.vo.menu.AddMenuVO;
import com.aus.vo.menu.ListMenuVO;
import com.aus.vo.menu.UpdateMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * Created by xy on 2017/11/20.
 */

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = Route.ADD_MENU)
    public Map<String, Object> addMenu(AddMenuVO addMenuVO){
        return menuService.addMenu(addMenuVO);
    }

    @RequestMapping(value = Route.LIST_MENU)
    public Map<String, Object> listMenu(ListMenuVO listMenuVO, PageUtil pageUtil){
        return menuService.listMenu(listMenuVO, pageUtil);
    }

    @RequestMapping(value = Route.DEL_MENU)
    public Map<String, Object> delMenu(@PathVariable(value = "id") Integer id){
        return menuService.delMenu(id);
    }

    @RequestMapping(value = Route.UPDATE_MENU)
    public Map<String, Object> updateMenu(@RequestBody UpdateMenuVO updateMenuVO){
        return menuService.updateMenu(updateMenuVO);
    }

    @RequestMapping(value = Route.CASCADE_MENU)
    public Map<String, Object> cascadeMenu(){
        return menuService.cascadeMenu();
    }

}
