package com.xuanxuan001.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @Author: 轩轩
 * @Date: 2020/3/10 17:33
 * @description: 临时的类
 */
@Controller
public class ConvertController {


    /**
     * @param name 名字
     * @param age 年龄
     * @param birth 生日
     * @DateTimeFormat  针对具体的参数，指定一个转换格式
     */
    @RequestMapping("/convert1.do")
    public String convertDate(String name , int age , @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date birth){
        System.out.println("name："+name);
        System.out.println("age："+age);
        System.out.println("birth："+birth);
        return "index";
    }
}
