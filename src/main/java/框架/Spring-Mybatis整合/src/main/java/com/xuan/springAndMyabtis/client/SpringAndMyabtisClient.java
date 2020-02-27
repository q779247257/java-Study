package com.xuan.springAndMyabtis.client;

import com.xuan.springAndMyabtis.domian.User;
import com.xuan.springAndMyabtis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: 轩轩
 * @Date: 2020/2/27 11:12
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringAndMyabtisClient {

    @Autowired private UserService userService;

    @Test
    public void getById(){
        User byId = userService.getById(1);

        System.out.println("info:"+byId);
    }
}
