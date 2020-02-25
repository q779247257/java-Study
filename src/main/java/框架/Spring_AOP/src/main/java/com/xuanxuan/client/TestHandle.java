package com.xuanxuan.client;

import com.xuanxuan.service.UserLogin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: 轩轩
 * @Date: 2020/2/25 15:42
 * @description:
 */
//创建容器
@RunWith(SpringJUnit4ClassRunner.class)
//指定创建容器时使用哪个配置文件
@ContextConfiguration("classpath:applicationContext.xml")
public class TestHandle {
    @Autowired
    private UserLogin uerLogin;

//    测试前置和后置通知
    @Test
    public void Test001(){
        uerLogin.userLogin();
    }

//    异常通知 和 最终稿通知 示例示例
    @Test
    public void Test002(){
        uerLogin.thorwError();
    }
}
