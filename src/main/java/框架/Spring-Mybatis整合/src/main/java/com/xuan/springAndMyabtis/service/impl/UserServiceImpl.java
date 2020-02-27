package com.xuan.springAndMyabtis.service.impl;

import com.xuan.springAndMyabtis.dao.UserMapper;
import com.xuan.springAndMyabtis.domian.User;
import com.xuan.springAndMyabtis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 轩轩
 * @Date: 2020/2/27 11:20
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired private UserMapper userMapper;

    @Override
    public User getById(Integer userId) {
        return userMapper.getById(userId);
    }
}
