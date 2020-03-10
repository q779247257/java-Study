package com.qf.dao;

import com.qf.pojo.SsmUser;

import java.util.List;

/**
 * @Author: 轩轩
 * @Date: 2020/3/6 15:59
 * @description:
 */
public interface SsmUserDao {
    /**
     * 根据user中的属性进行查询，没有则不加条件（只查询delete = 0 的用户）
     * username 账号
     * password 密码
     * and user_email （用户邮箱）
     * and user_call （用户名称，模糊搜索）
     * @param findUser 查询的pojo
     * @return 返回到条件查询的pojo
     */
    SsmUser findsingleByUser(SsmUser findUser);

    List<SsmUser> selectAll();

    Integer insertUser(SsmUser user);

    /**
     * 根据id修改用户信息 ，用户的属性中为null则不修改
     * @param user 用户POJO
     */
    Integer updateUserById(SsmUser user);
}
