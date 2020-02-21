package dao;

import domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: 轩轩
 * @Date: 2020/2/21 16:58
 * @description:
 */
public interface UserAnnoationMapper {
    @Results(id ="xuanxuan" ,
            value = {
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "address",property = "address"),
            @Result(column = "birthday",property = "birthday")
    })
    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user")
    @ResultMap("xuanxuan")
    List<User> findAlls();
}
