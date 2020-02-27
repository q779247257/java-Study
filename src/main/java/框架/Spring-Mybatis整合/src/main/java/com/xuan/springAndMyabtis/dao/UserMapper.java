package com.xuan.springAndMyabtis.dao;

import com.xuan.springAndMyabtis.domian.User;

/**
 * @Author: 轩轩
 * @Date: 2020/2/27 11:14
 * @description:
 */
public interface UserMapper {

    User getById(Integer userId);
}
