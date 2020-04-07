package com.qfedu.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
/**
 * @Author: 轩轩
 * @Date: 2020/4/7 23:40
 * @description: shiro 授权test测试类
 */
public class AuthorizationTest {

        public static void main(String[] args) {
            // 读取配置文件，初始化SecurityManager工厂
            IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro_text2.ini");
            // 获取securityManager实例
            SecurityManager securityManager = factory.getInstance();
            // 把securityManager实例绑定到SecurityUtils
            SecurityUtils.setSecurityManager(securityManager);

            // 获取Subjct对象
            Subject subject = SecurityUtils.getSubject();

            // 根据输入的用户名和密码生成token对象
            UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");

            try {
                // 身份认证
                subject.login(token);
                System.out.println("登录成功");
                // 是否有指定角色
                boolean ret = subject.hasRole("role5");
                System.out.println("角色是否有"+ret);

                // 是否有指定权限
                boolean ret2 = subject.isPermitted("user:select");
                System.out.println("select权限是否有："+ret2);
                // 是否有指定权限
                boolean ret22 = subject.isPermitted("user:ccc");
                System.out.println("ccc权限是否有："+ret22);


            } catch (AuthenticationException e) {
                e.printStackTrace();
                System.out.println("登录失败");
            }

        }
}
