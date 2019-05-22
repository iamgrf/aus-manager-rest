package com.aus.domain;

import com.aus.dao.UserDao;
import com.aus.domain.bo.RoleBO;
import com.aus.domain.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by xy on 2017/11/20.
 */

@Service
public class UserDomain {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDomain roleDomain;

    public List<UserBO> list(UserBO userBO) {
        List<UserBO> bos = userDao.list(userBO);
        bos.stream().forEach(bo -> {
            if (bo.getRoleBO().getId() == null){
                bo.setRoleBO(new RoleBO(-1, "未授权"));
            }else{
                bo.setRoleBO(roleDomain.findById(bo.getRoleBO().getId()));
            }
        });
        return bos;
    }

    public Integer save(UserBO userBO) {
        return userDao.save(userBO);
    }

    public UserBO findByAccount(String account) {
        UserBO userBO = new UserBO();
        userBO.setAccount(account);
        List<UserBO> userBOs = this.list(userBO);
        if (userBOs.size() > 0){
            if (userBOs.get(0).getRoleBO() != null){
                RoleBO roleBO = roleDomain.findById(userBOs.get(0).getRoleBO().getId());
                userBOs.get(0).setRoleBO(roleBO);
            }
            return userBOs.get(0);
        }
        return null;
    }

    public UserBO get(Integer id) {
        UserBO userBO = new UserBO();
        userBO.setId(id);
        List<UserBO> userBOs = this.list(userBO);
        if (userBOs.size() == 0){
            return null;
        }
        return userBOs.get(0);
    }

    public Integer update(UserBO userBO){
        Objects.requireNonNull(userBO.getId());
        return userDao.update(userBO);
    }

    public Integer authorized(UserBO userBO) {
        return update(userBO);
    }

    public Integer del(String account) {
        UserBO userBO = new UserBO();
        userBO.setAccount(account);
        return userDao.del(userBO);
    }
}
