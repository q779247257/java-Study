package com.qf.service;

import com.qf.pojo.SsmUser;

import java.util.List;

/**
 * @Author: 轩轩
 * @Date: 2020/3/6 15:58
 * @description:
 */
public interface SsmUserService {
    Integer login(String username, String password);

    SsmUser selectByName(String username);

    List<SsmUser> selectAll();

    //新增用户
    Integer innert(SsmUser user);

    /**
     * 根据id 查询用户
     * @param id 用户id
     * @return
     */
    SsmUser selectById(Integer id);

    /**
     * 根据id修改用户信息 ，用户的属性中为null则不修改
     * @param user 用户POJO
     */
    Integer updateUserById(SsmUser user);
}
