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
//    @Resource(name = "userDao")

    @Autowired//根据类型自动注入
//    @Qualifier("userDao")//指定名称
    private AnnotationUserDaoImpl userDao;

    public void  saveUser(){
        userDao.saveUser();
    }


}
