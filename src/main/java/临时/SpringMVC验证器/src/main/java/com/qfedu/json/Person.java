package com.qfedu.json;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Person {
    private Integer age;
    private String name;
    // 注意：配置timezone，否则时间少8个小时
    // 既写配置，又使用注解，注解生效
    @JsonFormat(pattern = "yyyy-MM-dd HHmmss", timezone = "GMT+8")
    private Date birth;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
