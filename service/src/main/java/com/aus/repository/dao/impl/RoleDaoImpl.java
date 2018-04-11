package com.aus.repository.dao.impl;

import com.aus.dao.RoleDao;
import com.aus.domain.bo.RoleBO;
import com.aus.repository.db.mybaits.RoleMapper;
import com.aus.repository.po.RolePO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by xy on 2017/11/22.
 */

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Integer add(RoleBO roleBO) {
        RolePO menuPO = convert(roleBO);
        return roleMapper.insert(menuPO);
    }

    @Override
    public List<RoleBO> list(RoleBO roleBO) {
        RolePO rolePO = convert(roleBO);
        List<RolePO> rolePOs =  roleMapper.select(rolePO);

        List<RoleBO> roleBOs = new ArrayList<>(rolePOs.size());
        for (int i = 0; i < rolePOs.size(); i++) {
            roleBOs.add(convert(rolePOs.get(i)));
        }
        return roleBOs;
    }

    @Override
    public Integer save(RoleBO roleBO) {
        RolePO rolePO = convert(roleBO);
        return roleMapper.insert(rolePO);
    }

    @Override
    public Integer update(RoleBO roleBO) {
        RolePO rolePO = convert(roleBO);
        return roleMapper.update(rolePO);
    }

    @Override
    public Integer delete(RoleBO roleBO) {
        RolePO rolePO = convert(roleBO);
        return roleMapper.delete(rolePO);
    }

    private RolePO convert(RoleBO roleBO){
        RolePO rolePO = new RolePO();
        BeanUtils.copyProperties(roleBO, rolePO);
        return rolePO;
    }

    private RoleBO convert(RolePO rolePO){
        RoleBO roleBO = new RoleBO();
        BeanUtils.copyProperties(rolePO, roleBO);
        return roleBO;
    }

}
