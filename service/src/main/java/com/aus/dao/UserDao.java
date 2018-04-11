package com.aus.dao;

import com.aus.domain.bo.UserBO;

import java.util.List;

/**
 * Created by xy on 2017/11/21.
 */
public interface UserDao {
    List<UserBO> list(UserBO userBO);

    Integer save(UserBO userBO);

    Integer update(UserBO userBO);

    Integer del(UserBO userBO);
}
