package com.xuanmvc.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Author: 轩轩
 * @Date: 2020/3/2 15:40
 * @description:
 */

@ControllerAdvice//对控制器进行增强
@EnableWebMvc//开启对 SpringMVC的支持
public class MyExecptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleException(Exception e){
//        System.out.println("全局异常：" + e);
//
//        ModelAndView modelAndView = new ModelAndView("/error");
//        modelAndView.addObject("error",e);
//        return modelAndView;
//    }
}
