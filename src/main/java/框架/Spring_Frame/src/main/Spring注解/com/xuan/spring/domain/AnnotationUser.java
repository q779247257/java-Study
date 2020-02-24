package com.xuan.spring.domain;

/**
 * @Author: 轩轩
 * @Date: 2020/2/24 10:34
 * @description:
 */
public class AnnotationUser {
    private String username;
    private String emall;
    private String phone;

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

    public AnnotationUser(String username, String emall, String phone) {
        this.username = username;
        this.emall = emall;
        this.phone = phone;
    }
}
