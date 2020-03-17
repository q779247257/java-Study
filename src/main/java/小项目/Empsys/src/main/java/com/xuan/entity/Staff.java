package com.xuan.entity;

import java.io.Serializable;

/**
 * (Staff)实体类
 *
 * @author makejava
 * @since 2020-03-17 10:40:49
 */
public class Staff implements Serializable {
    private static final long serialVersionUID = 838524597146151335L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 名字
    */
    private String name;
    /**
    * 年龄
    */
    private Integer age;
    /**
    * 性别
    */
    private String sex;
    /**
    * 电话
    */
    private String phone;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}