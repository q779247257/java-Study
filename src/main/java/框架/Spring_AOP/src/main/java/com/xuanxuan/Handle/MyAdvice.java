package com.xuanxuan.Handle;

import org.springframework.stereotype.Component;

/**
 * @Author: 轩轩
 * @Date: 2020/2/25 15:23
 * @description:
 */
@Component
public class MyAdvice {
    public void before(){
        System.out.println("这是一个前置通知");
    }

    public void after(){
        System.out.println("这是一个后置通知");
    }

}
