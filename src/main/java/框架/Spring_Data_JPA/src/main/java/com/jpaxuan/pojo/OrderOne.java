package com.jpaxuan.pojo;

import javax.persistence.*;

/**
 * @Author: 轩轩
 * @Date: 2020/9/20 21:09
 * @description: 演示单项多对一
 */
@Entity
@Table(name = "jpa_orders_one")
public class OrderOne {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "order_name")
    private String orderName;


    public OrderOne() {
    }

    public OrderOne(String orderName) {
        this.orderName = orderName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public String toString() {
        return "OrderOne{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                '}';
    }
}
