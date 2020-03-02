package com.ssm.controller;

import com.ssm.pojo.SsmUser;
import com.ssm.service.SsmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: 轩轩
 * @Date: 2020/3/2 18:09
 * @description:
 */
@Controller
@RequestMapping("/user")
public class SsmUserController {
    @Autowired
    private SsmUserService userService;

    @RequestMapping("/selectAll")
    public ModelAndView selectAll(){
        List<SsmUser> users = userService.selectAll();
        ModelAndView modelAndView = new ModelAndView("/user");
        modelAndView.addObject("users",users);
        return modelAndView;
    }
}
