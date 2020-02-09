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


    //根据id查询UserDetail
    UserDetail selectById(Integer id);
}
