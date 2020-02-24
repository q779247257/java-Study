package com.xuan.spring.service;

import com.xuan.spring.dao.AnnotationUserDaoImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: 轩轩
 * @Date: 2020/2/24 10:37
 * @description:
 */
@Component("userServiceImpl")
public class AnnotationUserServiceImpl {
    @Resource(name = "userDao")
    private AnnotationUserDaoImpl userDao;

    public void  saveUser(){
        userDao.saveUser();
    }


}
