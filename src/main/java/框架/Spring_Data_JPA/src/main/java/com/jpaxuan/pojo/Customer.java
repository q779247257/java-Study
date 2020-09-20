package com.jpaxuan.pojo;

import javax.persistence.*;

/**
 * @Author: 轩轩
 * @Date: 2020/9/20 14:28
 * @description:
 */

@Table(name = "jpa_coutomer")
@Entity//标明这是一个持久化类
public class Customer {
    @Id//主键标识
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成方式
//    @Column(name = "id")//列名如果和属性一样的话就可以不写
    private Integer id;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private int age;

    public Customer(String lastName, String email, int age) {
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
