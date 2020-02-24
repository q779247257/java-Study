package com.xuan.spring.service;

import com.xuan.spring.dao.AnnotationUserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * @Author: 轩轩
 * @Date: 2020/2/24 10:37
 * @description:
 */
@Component("userServiceImpl")
public class AnnotationUserServiceImpl {

    @Autowired//根据类型自动注入
    private AnnotationUserDaoImpl annotationUserDao;

    public void  saveUser(){
        System.out.println("userServiceImpl被调用");
        annotationUserDao.saveUser();
    }


}
