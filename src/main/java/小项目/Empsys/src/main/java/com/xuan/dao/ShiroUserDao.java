package com.xuan.dao;

import com.xuan.entity.ShiroUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ShiroUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-12 22:02:43
 */
public interface ShiroUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    ShiroUser queryById(Integer uid);
    ShiroUser queryByName(String name);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ShiroUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param shiroUser 实例对象
     * @return 对象列表
     */
    List<ShiroUser> queryAll(ShiroUser shiroUser);

    /**
     * 新增数据
     *
     * @param shiroUser 实例对象
     * @return 影响行数
     */
    int insert(ShiroUser shiroUser);

    /**
     * 修改数据
     *
     * @param shiroUser 实例对象
     * @return 影响行数
     */
    int update(ShiroUser shiroUser);

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 影响行数
     */
    int deleteById(Integer uid);

}