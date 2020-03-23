package com.qfedu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (SsmUser)实体类
 *
 * @author makejava
 * @since 2020-03-16 16:48:16
 */
public class SsmUser implements Serializable {
    private static final long serialVersionUID = 187670952213061827L;
    
    private Integer id;
    /**
    * 账户名
    */
    private String username;
    /**
    * 密码
    */
    private String password;
    /**
    * 用户称呼
    */
    private String userNickname;
    /**
    * 账户状态  ture 1 正常 false 2 异常
    */
    private Integer userStatus;
    /**
    * 用户的头像图片url
    */
    private String userPicUrl;
    /**
    * 注册的时间
    */
    private Date createTime;
    /**
    * 最近的更新时间
    */
    private Date updateTime;
    /**
    * 用户的等级\水平
    */
    private Integer userLevel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserPicUrl() {
        return userPicUrl;
    }

    public void setUserPicUrl(String userPicUrl) {
        this.userPicUrl = userPicUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

}