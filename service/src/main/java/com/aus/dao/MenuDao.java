package com.aus.dao;

import com.aus.domain.bo.MenuBO;

import java.util.List;

/**
 * Created by xy on 2017/11/20.
 */
public interface MenuDao {
    Integer add(MenuBO menuBO);

    List<MenuBO> list(MenuBO menuBO);

    Integer delete(Integer id);

    Integer update(MenuBO menuBO);

}
