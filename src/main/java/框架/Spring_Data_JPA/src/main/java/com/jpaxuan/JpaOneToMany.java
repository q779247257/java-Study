package com.jpaxuan;

import com.jpaxuan.pojo.CustonmerToOrders;
import com.jpaxuan.pojo.OrderOne;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.Set;

/**
 * @Author: 轩轩
 * @Date: 2020/9/20 21:12
 * @description: JPA单向一对多示例
 */
public class JpaOneToMany {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;


    @Before
    public void init(){
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa_test");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();//开启事务.
    }

    @After
    public void destroy(){
        entityTransaction.commit();//提交事务

        //关闭管理器和工厂
        entityManager.close();
        entityManagerFactory.close();
    }

    /**
     *  单向 1 对 多 执行保存时候 一定会多出 update语句
     *  因为 多 的一端在插入时 不会同时插入外列
     */
    @Test
    public void testOneToManyPersist(){
        CustonmerToOrders custonmerToOrders = new CustonmerToOrders("一对多", "'xuanxuan163.com", 18, "补充信息", new Date(), new Date());

        OrderOne orderOne1 = new OrderOne("订单11");
        OrderOne orderOne2 = new OrderOne("订单22");

        //建立关系
        custonmerToOrders.getOrderOnes().add(orderOne1);
        custonmerToOrders.getOrderOnes().add(orderOne2);

        //新增数据
        entityManager.persist(custonmerToOrders);
        entityManager.persist(orderOne1);
        entityManager.persist(orderOne2);
    }

    /**
     * 单向多对一的查询时候,默认对多的一方使用懒加载
     */
    @Test
    public void testOneToManyFind(){
        CustonmerToOrders custonmerToOrders = entityManager.find(CustonmerToOrders.class, 43);

//        Set<OrderOne> orderOnes = custonmerToOrders.getOrderOnes();

//        orderOnes.forEach(item -> {
//            System.out.println(item);
//        });
    }

    /**
     * 若 删除1 的一方,则会把 关联的n的一方的外键删除
     * 只有配置在一的属性上配置      @OneToMany(cascade = {CascadeType.REMOVE}) 之后,才会把数据也删除
     */
    @Test
    public void testOneToManyRemove(){
        CustonmerToOrders custonmerToOrders = entityManager.find(CustonmerToOrders.class, 40);
        entityManager.remove(custonmerToOrders);

    }

    /**
     * 单向一对多的时候 可以修改持久态状态对象的 多的一方的数据 可直接改变数据库属性
     */
    @Test
    public void testOneToManyUpdate(){
        CustonmerToOrders custonmerToOrders = entityManager.find(CustonmerToOrders.class, 43);
        for (OrderOne orderOne : custonmerToOrders.getOrderOnes()) {
            orderOne.setOrderName("修改后多的一方");
        }

    }

}

