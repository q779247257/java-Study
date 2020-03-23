package com.xuan.service;

import com.xuan.entity.PageVo;
import com.xuan.entity.Staff;
import java.util.List;

/**
 * (Staff)表服务接口
 *
 * @author makejava
 * @since 2020-03-17 10:40:53
 */
public interface StaffService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Staff queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Staff> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param staff 实例对象
     * @return 实例对象
     */
    Staff insert(Staff staff);

    /**
     * 修改数据
     *
     * @param staff 实例对象
     * @return 实例对象
     */
    Staff update(Staff staff);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<Staff> queryAll();


    /**
     * 分页查询
     * @param page 当前页
     * @param siez 每页展示数量
     * @return 查询的数据
     */
    PageVo<Staff> queryAll(Integer page , Integer siez);

    /**
     * 批量增加
     * @param list 增加的数据POJO集合
     */
    public void addEmps(List<Staff> list);

}