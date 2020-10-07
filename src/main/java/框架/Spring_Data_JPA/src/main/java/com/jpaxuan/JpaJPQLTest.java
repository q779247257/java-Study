package com.jpaxuan;

import com.jpaxuan.pojo.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: 轩轩
 * @Date: 2020/10/8 0:38
 * @description:JPQL 单元测试
 */
public class JpaJPQLTest {
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
     * 第一个例子  条件查询 符合条件的对象
     * 占位符的索引是从1开始
     */
    @Test
    public void test001(){
        String jpql = " from Customer c WHERE c.age > ?2";
        Query query = entityManager.createQuery(jpql);
        query.setParameter(2,18);
        List<Customer> list = query.getResultList();
        list.forEach(item -> System.out.println(item));
    }


    /** 获取部分属性
     * 默认情况下 若只查询部分属性 则将返回 object 类型的结果 或者 Object 的list
     * */
    @Test
    public void testPartlyProoerties(){
//        String jpql = "select c.lastName,c.age FROM Customer c where c.id = ?1";        //默认情况返回Object

        //返回对象的模式 注意 主要对应的构造器
        String jpql = "select new Customer (c.lastName,c.age) FROM Customer c where c.id = ?1";

        Query query = entityManager.createQuery(jpql);
        query.setParameter(1,1);
        List list = query.getResultList();
        list.forEach(item -> System.out.println(item));
    }





}
