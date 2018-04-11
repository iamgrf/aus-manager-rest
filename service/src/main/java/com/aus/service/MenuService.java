package com.aus.service;

import com.aus.util.PageUtil;
import com.aus.vo.menu.AddMenuVO;
import com.aus.vo.menu.ListMenuVO;
import com.aus.vo.menu.UpdateMenuVO;

import java.util.Map;

/**
 * Created by xy on 2017/11/20.
 */
public interface MenuService {
    Map<String,Object> addMenu(AddMenuVO addMenuVO);

    Map<String,Object> listMenu(ListMenuVO listMenuVO, PageUtil pageUtil);

    Map<String,Object> cascadeMenu();

    Map<String,Object> delMenu(Integer id);

    Map<String,Object> updateMenu(UpdateMenuVO updateMenuVO);
}
