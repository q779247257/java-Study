package dao;

import domain.UserLogin;

/**
 * @Author: 轩轩
 * @Date: 2020/2/3 15:03
 * @description:
 */
public interface UserLoginMapper {

    //根据id查询用户登录
    UserLogin findByid(Integer id);
}
