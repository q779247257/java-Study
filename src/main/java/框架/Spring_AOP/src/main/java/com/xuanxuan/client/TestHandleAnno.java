package com.xuanxuan.client;

import com.xuanxuan.service.UserLogin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: 轩轩
 * @Date: 2020/2/25 17:37
 * @description:
 */
//创建容器
@RunWith(SpringJUnit4ClassRunner.class)
//指定创建容器时使用哪个配置文件
@ContextConfiguration("classpath:applicationContext-anno.xml")
public class TestHandleAnno {
    /**
     * 注解模式的缺点：spring的通知顺序乱了，前置->后置->最终 或 前置->异常->最终
     *                 现在是前置->最终->异常  这是springAOP注解模式的一个缺点，如何避免这个缺点
     *                两种解决方案：
     *                      1.使用xml配置的方式，顺序是没有问题的
     *                      2.使用注解的环绕通知
     */
    @Autowired
    private UserLogin uerLogin;
    @Test
    public void TestAnno(){
        //前后异终通知
        uerLogin.uerrOutLogin();

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------");
        //环绕通知
        uerLogin.around();
    }
}
