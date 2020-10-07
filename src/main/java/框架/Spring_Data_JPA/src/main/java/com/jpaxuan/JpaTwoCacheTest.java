package com.jpaxuan;

import com.jpaxuan.pojo.Category;
import com.jpaxuan.pojo.Customer;
import com.jpaxuan.pojo.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @Author: 轩轩
 * @Date: 2020/10/7 23:48
 * @description: Jpa二级关系单元测试
 */
public class JpaTwoCacheTest {
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
     *  第一次查询的时候  从数据中查询
     *  第二次查询的时候 从一级缓存中查询
     *  所以只会执行一条sql
     */
    @Test
    public void test001(){
        Customer customer1 = entityManager.find(Customer.class, 1);
        Customer customer2 = entityManager.find(Customer.class, 1);
    }

    /**
     *  第一次查询的时候  从数据中查询
     *  关闭重启 对象管理器 会清除一级缓存
     *  所以 这个第二次查询的时候
     *  会出现执行sql
     */
    @Test
    public void test002(){
        Customer customer1 = entityManager.find(Customer.class, 1);


        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();//开启事务.
        Customer customer2 = entityManager.find(Customer.class, 1);
    }



}
