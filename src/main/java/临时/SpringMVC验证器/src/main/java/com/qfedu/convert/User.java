package com.qfedu.convert;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

public class User {
    //  判断是否为空
    // 如果没有设置message，验证失败的提示，使用默认数据
    // 如果从我们自己提供的多语言文件汇总获取信息，使用语法：{key值}
    @NotEmpty(message = "{name.empty}")
    private String name;
    // 设置最小值和最大值验证
    @Min(value = 1, message ="年龄的最小值为1")
    @Max(value = 99,message = "年龄最大为99")
    private int age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
