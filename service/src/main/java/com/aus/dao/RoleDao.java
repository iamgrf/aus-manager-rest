package com.aus.dao;

import com.aus.domain.bo.RoleBO;

import java.util.List;

/**
 * Created by xy on 2017/11/22.
 */
public interface RoleDao {
    List<RoleBO> list(RoleBO roleBO);

    Integer add(RoleBO roleBO);

    Integer save(RoleBO roleBO);

    Integer update(RoleBO roleBO);

    Integer delete(RoleBO roleBO);
}
