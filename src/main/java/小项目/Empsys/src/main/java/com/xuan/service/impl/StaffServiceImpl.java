package com.xuan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuan.entity.PageVo;
import com.xuan.entity.Staff;
import com.xuan.dao.StaffDao;
import com.xuan.service.StaffService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Staff)表服务实现类
 *
 * @author makejava
 * @since 2020-03-17 10:40:53
 */
@Service
public class StaffServiceImpl implements StaffService {
    @Resource
    private StaffDao staffDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Staff queryById(Integer id) {
        return this.staffDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Staff> queryAllByLimit(int offset, int limit) {
        return this.staffDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param staff 实例对象
     * @return 实例对象
     */
    @Override
    public Staff insert(Staff staff) {
        this.staffDao.insert(staff);
        return staff;
    }

    /**
     * 修改数据
     *
     * @param staff 实例对象
     * @return 修改之后的实例对象
     */
    @Override
    public Staff update(Staff staff) {
        this.staffDao.update(staff);
        return this.queryById(staff.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.staffDao.deleteById(id) > 0;
    }

    @Override
    public List<Staff> queryAll() {
        List<Staff> staff = staffDao.queryAll(new Staff());
        return staff;
    }

    /**
     * @param page 当前页
     * @param siez 每页展示数量
     * @return 查询的数据
     * 总页数公式：totalRecord是总记录数；pageSize是一页分多少条记录
     * int totalPageNum = (总记录数 +每页展示条数 - 1) / 每页展示条数;
     */
    @Override
    public PageVo<Staff> queryAll(Integer page, Integer siez) {
        PageVo<Staff> staffPageVo = new PageVo<>();

        //查询数据
        PageHelper.startPage(page,siez);
        List<Staff> staff = staffDao.queryAll(new Staff());
        Page pages = (Page) staff;

//        long total = pages.getTotal();//总条数
//        System.out.println("总条数为："+total);

           //当前页数
        pages.getPageSize(); //每页展示数量


        staffPageVo.setPageList(staff);//设置查询出的数据
        staffPageVo.setCurrentPage(pages.getPageNum());//设置当前页
        staffPageVo.setTotalPage(pages.getPages());//最大页
        return staffPageVo;
    }
}