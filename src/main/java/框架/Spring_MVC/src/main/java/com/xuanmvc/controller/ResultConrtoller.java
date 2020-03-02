package com.xuanmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 轩轩
 * @Date: 2020/3/2 14:27
 * @description: result风格需要再加上 @ResponseBody
 * - GET用来获取资源
 * - POST用来创建新资源
 * - PUT用来更新资源
 * - DELETE用来删除资源
 */
@Controller
@RequestMapping("/result")
public class ResultConrtoller {

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String insert(@RequestParam("name") String username){
        System.out.println("username:"+username);
        return "hellow";
    }

    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public String update(@RequestParam("name") String username){
        System.out.println("username:"+username);
        return "hellow";
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id){
        System.out.println("id:"+id);
        return "hellow";
    }
}
