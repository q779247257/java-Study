package com.qfedu.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * @Author: 轩轩
 * @Date: 2020/4/7 22:50
 * @description: shiro的认证test类
 */
public class AuthenticationTest {
    public static void main(String[] args) {
        // 读取配置文件，初始化SecurityManager工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro_text.ini");
        // 获取securityManager实例
        SecurityManager securityManager = factory.getInstance();
        // 把securityManager实例绑定到SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
        //获取Subjct对象主题
        Subject subject = SecurityUtils.getSubject();
        // 根据输入的用户名和密码生成token对象
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "12345");
        try {
            // 身份认证
            subject.login(token);
            System.out.println("登录成功");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败");
        }
    }
}
