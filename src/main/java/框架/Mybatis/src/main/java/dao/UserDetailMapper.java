package dao;

import domain.UserDetail;

import java.util.List;

/**
 * @Author: 轩轩
 * @Date: 2020/2/3 15:11
 * @description:
 */
public interface UserDetailMapper {
    List<UserDetail> selectAll();


    //根据id查询UserDetail 利用懒加载2条的sql语句
    UserDetail selectById(Integer id);

    //根据id查询UserDetail 利用resultMap进行嵌套对象   只使用一条SQL 语句
    UserDetail selectByIdOne(Integer id);



}
