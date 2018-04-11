package com.aus.repository.db.mybaits;

import com.aus.domain.bo.RoleBO;
import com.aus.repository.po.RolePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by xy on 2017/11/22.
 */

@Mapper
public interface RoleMapper {
    Integer insert(RolePO rolePO);

    List<RolePO> select(RolePO rolePO);

    Integer update(RolePO rolePO);

    Integer delete(RolePO rolePO);
}
