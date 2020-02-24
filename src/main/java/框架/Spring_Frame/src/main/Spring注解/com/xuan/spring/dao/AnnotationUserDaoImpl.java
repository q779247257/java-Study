package com.xuan.spring.dao;

import com.xuan.spring.domain.AnnotationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @Author: 轩轩
 * @Date: 2020/2/24 10:37
 *
 * @Component: bean id = userDao
 */
@Repository
public class AnnotationUserDaoImpl implements AnnotationUserDao {
@Autowired//根据类型获取
@Qualifier("userPojo")//根据名字获取
private AnnotationUser annotationUser;
    @Override
    public void saveUser() {
        System.out.println("增加了User");
        System.out.println("增加了@Value" +
                "注入的userUser:"+annotationUser);
    }

}
