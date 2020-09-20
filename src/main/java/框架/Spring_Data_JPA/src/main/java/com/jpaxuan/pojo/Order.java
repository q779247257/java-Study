package com.jpaxuan.pojo;

import javax.persistence.*;

/**
 * @Author: 轩轩
 * @Date: 2020/9/20 20:30
 * @description:
 */
@Entity
@Table(name = "jpa_orders")
public class Order {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "order_name")
    private String orderName;

    @JoinColumn(name = "customer_id")//映射外键
    @ManyToOne(fetch = FetchType.LAZY)//映射多对一的关联关系  FetchType.LAZY是懒加载的加载策略
    private Customer customer;

    public Order(String orderName, Customer customer) {
        this.orderName = orderName;
        this.customer = customer;
    }

    public Order() {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                ", customer=" + customer +
                '}';
    }
}
