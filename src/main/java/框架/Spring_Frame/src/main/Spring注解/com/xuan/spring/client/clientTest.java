package com.xuan.spring.client;

import com.xuan.spring.domain.AnnotationUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.xuan.spring.service.AnnotationUserServiceImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: 轩轩
 * @Date: 2020/2/24 10:28
 * @description: 加完 @RunWith 和 @ContextConfiguration之后
 * 不用再 new ClassPathXmlApplicationContext("Spring-Annotation.xml");
 * 使用工厂加载配置文件
 *
 */

//创建容器
@RunWith(SpringJUnit4ClassRunner.class)
//指定创建容器时使用哪个配置文件
@ContextConfiguration("classpath:Spring-Annotation.xml")
public class clientTest {

    @Autowired
    private AnnotationUser user;
    @Autowired
    private AnnotationUserServiceImpl annotationUserService;

    /**
     * 通过Spring注解来实现LOC
     */
    @Test
    public void Test001(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Annotation.xml");
        //getBean 可以使用配置文件中的id值,也可以使用配置文件的name值.
        AnnotationUserServiceImpl userServiceImpl = applicationContext.getBean("userServiceImpl", AnnotationUserServiceImpl.class);
        userServiceImpl.saveUser();
    }

    @Test
    public void Test002(){
        annotationUserService.saveUser();
        System.out.println("user:"+user);
    }
}
