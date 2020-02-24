package com.xuan.spring.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Author: 轩轩
 * @Date: 2020/2/24 10:34
 * @description:
 */
@Component("userPojo")
@Scope("prototype")
public class AnnotationUser {
    private @Value("轩轩") String username;
    private @Value("邮箱")String emall;
    private @Value("手机号")String phone;





    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmall() {
        return emall;
    }

    public void setEmall(String emall) {
        this.emall = emall;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AnnotationUser() {
    }

    public AnnotationUser(String username, String emall, String phone) {
        this.username = username;
        this.emall = emall;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "AnnotationUser{" +
                "username='" + username + '\'' +
                ", emall='" + emall + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
