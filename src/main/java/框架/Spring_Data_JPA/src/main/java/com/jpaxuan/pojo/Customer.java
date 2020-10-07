package com.jpaxuan.pojo;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;

/**
 * @Author: 轩轩
 * @Date: 2020/9/20 14:28
 * @description:
 */

@Table(name = "jpa_coutomer")//表示数据库中的表名
@Entity//标明这是一个持久化类
public class Customer {
    @Id//主键标识
//    @TableGenerator(name = "ID_GENERATOR",
//                    table = "jpa_id",//表名
//                    pkColumnName = "PK_NAME",
//            pkColumnValue = "CUSTOMER_ID",
//            valueColumnName = "PK_VALUE",
//            allocationSize = 100//每次张100
//    )
    /**
     * –IDENTITY：采用数据库ID自增长的方式来自增主键字段，Oracle 不支持这种方式；
     * –AUTO： JPA自动选择合适的策略，是默认选项；
     * –SEQUENCE：通过序列产生主键，通过@SequenceGenerator 注解指定序列名，MySql不支持这种方式
     * –TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植。
     */
//    @GeneratedValue(strategy = GenerationType.TABLE,generator = "ID_GENERATOR")//主键生成方式
    @GeneratedValue(strategy = GenerationType.AUTO)//主键生成方式
//    @Column(name = "id")//列名如果和属性一样的话就可以不写
    private Integer id;
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

    public Customer(String lastName, String email, int age) {
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.createdTime= new Date();
        this.birth = new Date();
    }

    public Customer() {
    }

    public Customer(String lastName, int age) {
        this.lastName = lastName;
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
