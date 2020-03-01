package com.xuanmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 轩轩
 * @Date: 2020/2/27 15:26
 * @description:
 *  如果@requestParam注解的参数是int类型，并且required=false，
 *  此时如果不传参数的话，会报错。原因是，required=false时，不
 *  传参数的话，会给参数赋值null，这样就会把null赋值给了int，
 *  因此会报错。
 */
@Controller
@RequestMapping("/hellow")
public class HellowController {

    @RequestMapping("/word")
    public String word(){
        System.out.println("接受到请求————————————————————————————————");
        return "hellow";
    }

    // required=false表示不传的话，会给参数赋值为null，required=true就是必须要有
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login( @RequestParam("name") String username, String password){
        System.out.println("接受到请求————————————————————————————————");
        System.out.println("接受到请求username："+username);
        System.out.println("接受到请求password："+password);
        return "hellow";
    }


    //defaultValue 默认值
    @RequestMapping(value = "/list")
    public String list( @RequestParam(defaultValue = "66666") String textValue){
        System.out.println("textValue："+textValue);
        return "hellow";
    }

    //路径取参
    @RequestMapping(value = "/list/{id}")
    public String pathData(@PathVariable("id") String id){
        System.out.println("路径内容为："+id);
        return "hellow";
    }

    //获取cookie 和 请求头 header
    @RequestMapping(value = "/cookie")
    public String testHeader(@CookieValue("JSESSIONID")String cookie,
                             @RequestHeader("User-Agent")String header)
    {
        System.out.println("cookie:"+cookie);
        System.out.println("header:"+header);
        return "hellow";
    }

    //Spring MVC 实现重定向
    @RequestMapping(value = "/redirect")
    public String testRedirect(){
        System.out.println("重定向触发");
        return "redirect:/hellow/word";
    }

}
