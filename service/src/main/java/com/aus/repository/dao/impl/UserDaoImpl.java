package com.aus.repository.dao.impl;

import com.aus.dao.UserDao;
import com.aus.domain.bo.RoleBO;
import com.aus.domain.bo.UserBO;
import com.aus.repository.db.mybaits.UserMapper;
import com.aus.repository.po.UserPO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xy on 2017/11/21.
 */

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserBO> list(UserBO userBO) {
        UserPO userPO = convert(userBO);
        List<UserPO> userPOs = userMapper.select(userPO);
        List<UserBO> userBOs = new ArrayList<>(userPOs.size());
        for (int i = 0; i < userPOs.size(); i++) {
            userBOs.add(convert(userPOs.get(i)));
        }
        return userBOs;
    }

    @Override
    public Integer save(UserBO userBO) {
        UserPO userPO = convert(userBO);
        return userMapper.insert(userPO);
    }

    @Override
    public Integer update(UserBO userBO) {
        UserPO userPO = convert(userBO);
        return userMapper.update(userPO);
    }

    @Override
    public Integer del(UserBO userBO) {
        UserPO userPO = convert(userBO);
        return userMapper.delete(userPO);
    }

    private UserPO convert(UserBO userBO){
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(userBO, userPO);
        if (userBO.getRoleBO() != null){
            userPO.setRoleId(userBO.getRoleBO().getId());
        }
        return userPO;
    }

    private UserBO convert(UserPO userPO){
        UserBO userBO = new UserBO();
        BeanUtils.copyProperties(userPO, userBO);
        userBO.setRoleBO(new RoleBO(userPO.getRoleId()));
        return userBO;
    }

}
