package com.xuanxuan.service;

import org.springframework.stereotype.Service;

/**
 * @Author: 轩轩
 * @Date: 2020/2/25 15:40
 * @description:
 */
@Service
public class UserLogin {
    public void userLogin(){
        System.out.println("用户登录了");
    }

    public void uerrOutLogin(){
        System.out.println("用户退出了");
    }
    public void thorwError(){
        int e = 1/0;
        System.out.println("异常通知测试");
    }

    public void around(){
        System.out.println("环绕通知测试");
        int e = 1/0;
    }
}
