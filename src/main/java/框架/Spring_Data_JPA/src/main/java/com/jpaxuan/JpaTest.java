package com.jpaxuan;

import com.jpaxuan.pojo.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;

/**
 * @Author: 轩轩
 * @Date: 2020/9/20 14:36
 * @description:
 */
public class JpaTest {
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
     * find 是即时加载
     * getReference 是懒加载 需要的时候才加载
     */
    @Test
    public void testFind(){
        Customer customer = entityManager.find(Customer.class,1);
        System.out.println("-----------------------------------------");
        System.out.println(customer);
    }

    @Test
    public void testGetReference(){
        Customer customer = entityManager.getReference(Customer.class,1);
        System.out.println("-----------------------------------------");
        System.out.println(customer);
    }

    /**
     * persist 新增  使对象由临时状态变为持久化状态
     */
    @Test
    public void testPersistence(){
        Customer customer = new Customer("lastName","xuanxuan.163.com",18);
//        customer.setId(11111); //设置id会报错
        entityManager.persist(customer);
        System.out.println(customer.getId());
    }

    /**
     * 删除对象
     * 注意： JPA 的remove 只能删除持久化对象
     */
    @Test
    public void testRemove(){
        //直接创建对象 删除会报错  无法删除有理对象
//        Customer customer = new Customer();
//        customer.setId(2);

        Customer customer = entityManager.find(Customer.class, 2);
        entityManager.remove(customer);
    }

    /**
     * 插入和更新
     * 1、若传入的是一个临时对象 则新增
     *  会创建一个新的对象 把临时对象的属性复制到新的对象中，然后对新的对象执行 insert 持久化操作
     */
    @Test
    public void testMerge1(){
        Customer customer = new Customer("lastNmae", "779247267@163.com", 18);
        Customer merge = entityManager.merge(customer);
        System.out.println("========================================");
        System.out.println(merge.getId());
    }

    /**
     * 插入和更新
     * 1、若传入的是一个临时对象有id 存在则修改  不存在则新增
     */
    @Test
    public void testMerge2(){
        Customer customer = new Customer("修改", "779247267@163.com", 18);
        customer.setId(2);
        Customer merge = entityManager.merge(customer);
        System.out.println("========================================");
        System.out.println(merge.getId());
    }
    @Test
    public void testMerge3(){
        Customer customer = entityManager.find(Customer.class, 1);
        customer.setAge(9);
        Customer merge = entityManager.merge(customer);
        System.out.println("==============================  ");
        System.out.println(customer == merge);

    }

    /**
     * 同步上下文环境，将未保存的实体状态信息保存到 da 中
     * FlushModeType.AUTO 为自定更新数据库实体
     * FlushModeType.COMMIT 直到提交事务的时候才更新数据库记录
     */
    @Test
    public void testFlush(){
        Customer customer = entityManager.find(Customer.class, 1);
        customer.setAge(99);
        entityManager.flush();
//        FlushModeType flushMode = entityManager.getFlushMode();
//        entityManager.setFlushMode(FlushModeType.COMMIT);
    }

    /**
     * refresh 同步对象状态
     */
    @Test
    public void testRefresh(){
        Customer customer = entityManager.find(Customer.class, 1);
        customer = entityManager.find(Customer.class, 1);//因为有缓存所有只会执行一条sql
        customer.setAge(18);
        entityManager.refresh(customer); //加上 refresh后会执行2条sql数据  再发送看对象是否是最新的
    }



}
