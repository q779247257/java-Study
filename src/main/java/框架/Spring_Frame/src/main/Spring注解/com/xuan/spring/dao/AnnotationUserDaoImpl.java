package com.xuan.spring.dao;

import org.springframework.stereotype.Component;

/**
 * @Author: 轩轩
 * @Date: 2020/2/24 10:37
 *
 * @Component: bean id = userDao
 */
@Component("userDao")
public class AnnotationUserDaoImpl implements AnnotationUserDao {

    @Override
    public void saveUser() {
        System.out.println("增加了User");
    }
}
