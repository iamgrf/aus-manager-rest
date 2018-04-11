package com.aus.controller;

import com.alibaba.fastjson.JSON;
import com.aus.service.MenuService;
import com.aus.util.PageUtil;
import com.aus.vo.menu.AddMenuVO;
import com.aus.vo.menu.ListMenuVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by xy on 2017/7/10.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Rollback(value = true)
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void addMenu(){
        AddMenuVO addMenuVO = new AddMenuVO();
        addMenuVO.setParentId(3);
        addMenuVO.setMenuName("查询用户");
        addMenuVO.setMenuCode("/userList");
        Map<String, Object> result = menuService.addMenu(addMenuVO);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void listMenu(){
        ListMenuVO listMenuVO = new ListMenuVO();
        listMenuVO.setParentId(-1);
        Map<String, Object> result = menuService.listMenu(listMenuVO, new PageUtil());
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void cascadeMenu(){
        Map<String, Object> result = menuService.cascadeMenu();
        System.out.println(JSON.toJSONString(result));
    }

}
