package com.xuan.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 轩轩
 * @Date: 2020/4/12 21:47
 * @description: 此类主要用来测试shiro的授权和认证
 *
 * @RequiresPermissions ：要求当前Subject在执行被注解的方法时具备一个或多个对应的权限。
 * @RequiresRoles ：要求当前Subject在执行被注解的方法时具备所有的角色，否则将抛出AuthorizationException异常。
 * @RequiresAuthentication：要求在访问或调用被注解的类/实例/方法时，Subject在当前的session中已经被验证。
 */
@Controller
@ControllerAdvice
public class UserController {

    /**
     * 登录
     * @param username 账户
     * @param password 密码
     * @param isRemember  是否记住我
     * @return 成功跳转到list jsp 页面
     */
    @RequestMapping("/login.do")
    public String login(String username , String password ,
                        @RequestParam(value = "isRemember",defaultValue = "0") Integer isRemember ){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //获得主题对象
        Subject subject = SecurityUtils.getSubject();

        //如果传过来为1 则页面勾选记住我
        if(isRemember == 1){
            System.out.println("开启shiro的记住我功能");
            //设置记住我
            token.setRememberMe(true);
        }
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
//    @RequiresPermissions("user:list")
    @RequestMapping("/test.do")
    public String test(){
        return "redirect:add.jsp";
    }

    @RequiresPermissions("user:ddd")
    @RequestMapping("/test2.do")
    public String test2(){
        return "redirect:add.jsp";
    }

    /** 使用注解的shiro ajax异常的时候 就使用异常捕捉 */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public String unauthorizedException(UnauthorizedException e){
        e.printStackTrace();
        return "not is perms";
    }

}
