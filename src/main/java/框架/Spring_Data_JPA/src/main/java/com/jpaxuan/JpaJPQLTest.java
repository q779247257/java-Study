package com.jpaxuan;

import com.jpaxuan.pojo.Customer;
import com.jpaxuan.pojo.CustonmerToOrders;
import org.hibernate.jpa.QueryHints;
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


    /** Jpql 使用 Order By */
    @Test
    public void testOrderBy(){
        String jpql = " from Customer c WHERE c.age > ?1 order by c.age DESC";
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1,12);

        List<Customer> list = query.getResultList();
        System.out.println(list.size());

    }

    /** 查询 order 数量 大于1 的 Customer 使用 gruop by */
    @Test
    public void testGroupBy(){
        String jpql = " select o.customer from Order o group by o.customer having count (o.id) > 1";
        Query query = entityManager.createQuery(jpql);

        List<Customer> list = query.getResultList();
        System.out.println(list.size());

    }

    /** 删除或更新操作 */
    @Test
    public void testUpdateOrDelete(){
        //更新数据
//        String jpql = "update Customer c set c.lastName = ?1 where c.id = ?2 ";
//        Query query = entityManager.createQuery(jpql).setParameter(1, "更新后的名字").setParameter(2, 1);
//        query.executeUpdate();//执行更新语句


        //删除数据
        String delJpql = "delete from Customer c where c.id = ?1";
        Query query1 = entityManager.createQuery(delJpql).setParameter(1,4);
        query1.executeUpdate();

    }

    /**
     * 左连接查询
     *
     * 注意 jpql 中 fetch 不可以去掉 这样得到的将是一个对象 如果去掉fetch 的话 得到的将会是一个 object
     */
    @Test
    public void testLeftJoIoinFetch(){
        String jpql = "select c from CustonmerToOrders c left join fetch c.orderOnes where c.id =?1";
        Query query = entityManager.createQuery(jpql).setParameter(1,43);
        CustonmerToOrders customer = (CustonmerToOrders)query.getSingleResult();
        System.out.println(customer.getOrderOnes());

    }

    /**
     * 子查询
     */
    @Test
    public void testSubQuery(){
        //查询所有 Custonmer 的lastName 为 多对11 的Order
        String jpql = "select o from Order o where o.customer = (select c from Customer c where c.lastName = ?1)";

        Query query = entityManager.createQuery(jpql).setParameter(1,"多对11");
        List resultList = query.getResultList();
        System.out.println(resultList);
    }

    /**
     * createNativeQuery 适用于本地sql 相当于在数据库中跑的sql语句
     */
    @Test
    public void testNativeQuery(){
        String sql = "select age FROM jpa_coutomer WHERE id = ?";

        Query query = entityManager.createNativeQuery(sql).setParameter(1,1);

        Object singleResult = query.getSingleResult();
        System.out.println(singleResult);
    }

    /**
     * 使用 NameQuery 进行查询  NameQuery 需要在 类上边进行标注 jpql 语句 和 名称 根据名称来获取
     */
    @Test
    public void testNameQuery(){
        Query testNameQuery = entityManager.createNamedQuery("testNameQuery").setParameter(1,3);
        Object singleResult = testNameQuery.getSingleResult();
        System.out.println(singleResult);
        List resultList = testNameQuery.getResultList();
        System.out.println(resultList);
    }

    /** 测试查询缓存*/
    @Test
    public void testQueryCache(){
        String jpql = " from Customer c WHERE c.age > ?1";
        Query query = entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE,true);
        query.setParameter(1,18);

        List<Customer> list = query.getResultList();
        System.out.println(list.size());

        //再查询一遍
        query = entityManager.createQuery(jpql).setParameter(1,18).setHint(QueryHints.HINT_CACHEABLE,true);
        list = query.getResultList();
        System.out.println(list.size());
    }





}
