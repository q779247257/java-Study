package com.xuan.spring.client;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.xuan.spring.service.AnnotationUserServiceImpl;

/**
 * @Author: 轩轩
 * @Date: 2020/2/24 10:28
 * @description:
 */
public class clientTest {

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
}
