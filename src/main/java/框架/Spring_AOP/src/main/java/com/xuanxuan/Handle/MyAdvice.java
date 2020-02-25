package com.xuanxuan.Handle;

import org.aspectj.lang.ProceedingJoinPoint;
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

    //异常通知
    public void throwing(){
        System.out.println("这是异常通知");
    }

    public void afterEnd(){
        System.out.println("这是最终通知");
    }

    public void around(ProceedingJoinPoint point) throws Throwable {
        try {
            System.out.println("这是环绕通知的前部分");
            Object proceed = point.proceed();
            System.out.println("这是环绕通知之后的部分");
        } catch (Exception e) {
            System.out.println("这是环绕通知的异常部分");
        } finally {
            System.out.println("这是环绕通知的最终部分");

        }

    }

}
