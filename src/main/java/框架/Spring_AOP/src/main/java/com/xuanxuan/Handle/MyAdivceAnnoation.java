package com.xuanxuan.Handle;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author: 轩轩
 * @Date: 2020/2/25 17:24
 * @description:
 */

@Component
@Aspect//标注此类是一个切面类
public class MyAdivceAnnoation {
    @Pointcut("execution(* com.xuanxuan.service.UserLogin.*(..))")
    public void ptl(){
        /**
         * 注解模式的缺点：spring的通知顺序乱了，前置->后置->最终 或 前置->异常->最终
         *                 现在是前置->最终->异常  这是springAOP注解模式的一个缺点，如何避免这个缺点
         *                两种解决方案：
         *                      1.使用xml配置的方式，顺序是没有问题的
         *                      2.使用注解的环绕通知
         */
    }

    @Before("ptl()")
    public void before(){
        System.out.println("这是一个前置通知");
    }

    @AfterReturning("ptl()")
    public void after(){
        System.out.println("这是一个后置通知");
    }

    //异常通知
    @AfterThrowing("ptl()")
    public void throwing(){
        System.out.println("这是异常通知");
    }

    @After("ptl()")
    public void afterEnd(){
        System.out.println("这是最终通知");
    }

    @Around(value = "execution(* com.xuanxuan.service.UserLogin.around(..))")
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
