package org.example.test;

import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @模块文件夹：DrugTest
 * @项目名称：drug-administration
 * @描述：
 * @作者 ：陈鸿凯
 * @date：2023.11.19.22:12
 */

@SpringBootTest
public class DrugTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void Test01(){

        List<User> users = userMapper.selectList(null);
        System.out.println("users = " + users);

        List<User> list = userService.list();
        System.out.println("list = " + list);

    }

}
