package com.jpaxuan;

import com.jpaxuan.pojo.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @Author: 轩轩
 * @Date: 2020/9/20 14:37
 * @description:
 */
public class Main {
    public static void main(String[] args) {

        //1、创建EntityMangaerFatory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_test");

        //2、创建EntityManger
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //3、开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //4、进行持久化操作
        Customer customer = new Customer("名字","xuanxuan@163.com",18);
        entityManager.persist(customer);

        //5、提交事务
        transaction.commit();

        //6、关闭 EntityManager
        entityManager.close();

        //7、关闭 创建EntityMangaerFatory
        entityManagerFactory.close();
    }
}
