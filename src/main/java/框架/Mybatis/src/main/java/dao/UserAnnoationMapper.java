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
            @Result(id=true,column = "id",property = "id" ),
            @Result(column = "name",property = "name"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "address",property = "address"),
            @Result(column = "birthday",property = "birthday")
    })
    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where id = #{id}")
    @ResultMap("xuanxuan")
    List<User> findById(Integer id);

    @Select("select * from user where name = #{name}")
    @ResultMap("xuanxuan")
    List<User> findByName(String name);
}
