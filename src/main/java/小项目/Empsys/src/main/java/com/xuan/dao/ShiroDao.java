package com.xuan.dao;

import com.xuan.entity.ShiroUser;

/**
 * @Author: 轩轩
 * @Date: 2020/4/10 22:56
 */
public interface ShiroDao {
    /**
     * 根据用户名查询用户对象
     * @param name
     * @return
     */
    ShiroUser findByName(String name);
}
