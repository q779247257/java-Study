package com.xuan.controller;

import com.xuan.entity.PageVo;
import com.xuan.entity.Staff;
import com.xuan.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Staff)表控制层
 *
 * @author makejava
 * @since 2020-03-17 10:40:54
 */
@Controller
@RequestMapping("/staff")
public class StaffController {
    /**
     * 服务对象
     */
    @Autowired
    private StaffService staffService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public Staff selectOne(Integer id) {
        return this.staffService.queryById(id);
    }

    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    public ModelAndView selectAll(){
        ModelAndView modelAndView = new ModelAndView();

       List<Staff> staffList =  staffService.queryAll();
        modelAndView.addObject("list",staffList);
        modelAndView.setViewName("list");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public List<Staff> findAll(){
        List<Staff> staffList =  staffService.queryAll();
        return staffList;
    }

    @ResponseBody
    @RequestMapping(value = "/selectById",method = RequestMethod.GET)
    public Staff selectById(Integer id){
        Staff staff = staffService.queryById(id);
        return staff;
    }

    @RequestMapping(value = "/updateById",method = RequestMethod.POST)
    public String selectById(Staff staff){
        System.out.println("接受的对象为："+staff);
        Staff update = staffService.update(staff);
        System.out.println("修改后的对象为："+staff);
        return "redirect:/list.jsp";
    }
    @ResponseBody
    @RequestMapping(value = "/insertByPojo",method = RequestMethod.POST)
    public Staff insertByPojo(Staff staff){
        Staff insert = staffService.insert(staff);
        System.out.println("新增侯的对象为："+insert);
        return insert;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteById",method = RequestMethod.GET)
    public boolean deleteById(Integer id){
        boolean isSuccess = staffService.deleteById(id);
        return isSuccess;
    }
    @ResponseBody
    @RequestMapping(value = "/page.do" , method = RequestMethod.GET)
    public PageVo<Staff> findByPage(@RequestParam(defaultValue = "1") Integer page , @RequestParam(defaultValue = "5") Integer siez){
        PageVo<Staff> staffPageVo = staffService.queryAll(page, siez);
        return staffPageVo;
    }
}