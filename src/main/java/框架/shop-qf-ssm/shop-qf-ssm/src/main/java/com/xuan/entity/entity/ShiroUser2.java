package com.xuan.entity.entity;

import java.io.Serializable;

/**
 * (ShiroUser2)实体类
 *
 * @author makejava
 * @since 2020-04-10 22:53:35
 */
public class ShiroUser2 implements Serializable {
    private static final long serialVersionUID = -11819179706883638L;
    
    private Integer userId;
    /**
    * 账户
    */
    private String username;
    /**
    * 密码
    */
    private String password;
    /**
    * 角色id
    */
    private Integer roleId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}