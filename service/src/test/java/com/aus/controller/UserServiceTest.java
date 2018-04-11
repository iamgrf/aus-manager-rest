package com.aus.controller;

import com.aus.service.UserService;
import com.aus.util.StringUtil;
import com.aus.vo.user.UpdatePasswordVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by xy on 2017/11/23.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Rollback(value = true)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void menus(){
        /*Map<String, Object> result = userService.menus("a");
        System.out.println(result);*/
    }

    /*@Test
    public void updatePassword(){
        UpdatePasswordVO updatePasswordVO = new UpdatePasswordVO();
        updatePasswordVO.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJhY2NvdW50XCI6XCJ0ZXN0XCIsXCJhdXRob3JpdHlDb2RlXCI6XCIxMTExMTAwMDAwMDAwMDAwMDAwMTEwMDAwMDAwXCJ9In0.C4omwTKGchFIss-6BzjVHgDSaxrwQT3T0BR8MS-NO2Y");
        updatePasswordVO.setOldPassword(StringUtil.md5("test"));
        updatePasswordVO.setNewPassword(StringUtil.md5("123"));
        Map<String, Object> result = userService.updatePassword(updatePasswordVO);
        System.out.println(result);
    }*/

}
