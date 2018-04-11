package com.aus.domain;

import com.aus.dao.RoleDao;
import com.aus.domain.bo.RoleBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by xy on 2017/11/22.
 */

@Service
public class RoleDomain {

    @Autowired
    private RoleDao roleDao;

    public List<RoleBO> list(RoleBO roleBO) {
        return roleDao.list(roleBO);
    }

    public RoleBO getByCode(String code) {
        RoleBO roleBO = new RoleBO();
        roleBO.setCode(code);
        List<RoleBO> roleBOs = this.list(roleBO);
        if (roleBOs.size() == 0){
            return null;
        }
        return roleBOs.get(0);
    }

    public Integer save(RoleBO roleBO) {
        return roleDao.save(roleBO);
    }

    public Integer update(RoleBO roleBO) {
        return roleDao.update(roleBO);
    }

    public Integer authorized(RoleBO roleBO) {
        return update(roleBO);
    }

    public RoleBO findById(Integer id) {
        RoleBO roleBO = new RoleBO();
        roleBO.setId(id);
        List<RoleBO> roleBOs = this.list(roleBO);
        if (roleBOs.size() == 0){
            return null;
        }
        return roleBOs.get(0);
    }

    public Integer delete(Integer id) {
        Objects.requireNonNull(id);
        return roleDao.delete(new RoleBO(id));
    }
}
