package com.aus.repository.db.mybaits;

import com.aus.repository.po.MenuPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by xy on 2017/11/20.
 */

@Mapper
public interface MenuMapper {
    Integer insert(MenuPO menuPO);

    List<MenuPO> select(MenuPO menuPO);

    Integer delete(Integer id);

    Integer update(MenuPO menuPO);
}
