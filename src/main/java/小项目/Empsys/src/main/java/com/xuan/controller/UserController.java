package com.xuan.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 轩轩
 * @Date: 2020/4/12 21:47
 * @description: 此类主要用来测试shiro的授权和认证
 */
@Controller
public class UserController {

    /**
     * 登录
     * @param username 账户
     * @param password 密码
     * @return 成功跳转到list jsp 页面
     */
    @RequestMapping("/login.do")
    public String login(String username , String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //获得主题对象
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
            //登录成功跳转到登录页面
            return "redirect:/list.jsp";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //抛异常 代表登录失败 跳转到login页面
            return "redirect:/login.jso";
        }
    }

    //需要 user:list 权限才能访问test.do
    @RequiresPermissions("user:list")
    @RequestMapping("/test.do")
    public String test(){
        return "redirect:add.jsp";
    }

    @RequiresPermissions("user:ddd")
    @RequestMapping("/test2.do")
    public String test2(){
        return "redirect:add.jsp";
    }
}
