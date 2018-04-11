package com.aus.repository.db.mybaits;

import com.aus.repository.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by xy on 2017/11/20.
 */

@Mapper
public interface UserMapper {
    Integer insert(UserPO userPO);

    List<UserPO> select(UserPO userPO);

    Integer update(UserPO userPO);

    Integer delete(UserPO userPO);
}
