package com.jpaxuan.pojo;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: 轩轩
 * @Date: 2020/9/20 21:05
 * @description:
 */
@Table(name = "jpa_coutomer_many")//表示数据库中的表名
@Entity//标明这是一个持久化类
public class CustonmerToOrders {
    @Id//主键标识
    @GeneratedValue(strategy = GenerationType.AUTO)//主键生成方式
    private Integer id;

    @JoinColumn(name = "coutomer_one_id")//一对多的关联外键
//    @OneToMany(fetch = FetchType.EAGER)//一对多映射的时候默认使用懒加载
    @OneToMany(cascade = {CascadeType.REMOVE})
    private Set<OrderOne> orderOnes = new HashSet<>();

    @Column(name = "last_name")
    private String lastName;
    /**
     * name 列明
     * nullable 非空约束
     * unique 唯一约束
     * length 长度约束
     */
    @Column(name = "email",nullable = false,length = 122 )
    private String email;
    private int age;

    @Transient//，不需要映射为数据库的一列可加上此注解 加此注解可以屏蔽    @Basic
    public String info;

    /**
     * 主要用来指定时间的类型
     * DATE 精确到天
     * DATETIME 精确到时间分秒
     * TIMESTAMP 时间卓
     */
    @Temporal(value = TemporalType.TIMESTAMP)//时间戳
    public Date createdTime;
    @Temporal(value = TemporalType.DATE)//精准到天
    public Date birth;

    public CustonmerToOrders(Set<OrderOne> orderOnes, String lastName, String email, int age, String info, Date createdTime, Date birth) {
        this.orderOnes = orderOnes;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.info = info;
        this.createdTime = createdTime;
        this.birth = birth;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }


    public CustonmerToOrders(String lastName, String email, int age, String info, Date createdTime, Date birth) {
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.info = info;
        this.createdTime = createdTime;
        this.birth = birth;
    }



    public CustonmerToOrders() {
    }

    public Set<OrderOne> getOrderOnes() {
        return orderOnes;
    }

    public void setOrderOnes(Set<OrderOne> orderOnes) {
        this.orderOnes = orderOnes;
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
