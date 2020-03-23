package com.qfedu.dao;

import com.qfedu.entity.SsmUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SsmUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-16 16:48:17
 */
public interface SsmUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SsmUser queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SsmUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param ssmUser 实例对象
     * @return 对象列表
     */
    List<SsmUser> queryAll(SsmUser ssmUser);

    /**
     * 新增数据
     *
     * @param ssmUser 实例对象
     * @return 影响行数
     */
    int insert(SsmUser ssmUser);

    /**
     * 修改数据
     *
     * @param ssmUser 实例对象
     * @return 影响行数
     */
    int update(SsmUser ssmUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}