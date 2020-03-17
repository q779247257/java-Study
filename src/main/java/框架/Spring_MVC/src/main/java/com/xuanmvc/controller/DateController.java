package com.xuanmvc.controller;

import com.xuanmvc.pojo.UserDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: 轩轩
 * @Date: 2020/3/13 17:27
 * @description:
 *

 */
@Controller
public class DateController {
    @ResponseBody
    @RequestMapping(value = "/date.do",method = RequestMethod.GET)
    public String dateTimeForam(UserDateTime user){
        System.out.println("user："+user);
        return "嘿嘿嘿";
    }

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ResponseBody
    @RequestMapping(value = "/date2.do",method = RequestMethod.POST)
    public String  convertDate2(String date){
        System.out.println("请求触发--------------------------------------------------");
        System.out.println("date:"+date);
        return "嘿嘿嘿";
    }

//    /**
//     *  从字面意思可以看出这个的作用是给Binder做初始化的，被此注解的方法可以对WebDataBinder初始化。webDataBinder是用于表单到方法的数据绑定的！
//     *  @InitBinder 只在@Controller中注解方法来为这个控制器注册一个绑定器初始化方法，方法只对本控制器有效。       
//     */
//    @InitBinder
//    public void initBinder(WebDataBinder binder){
////日期类型转换器
//        binder.registerCustomEditor(
//                Date.class,
//                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"), true)
//        );
//    }
}
