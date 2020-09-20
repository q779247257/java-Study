### JPA是什么？

 JPA是一个规范，JPA并未提供ORM实现，它只是制订了一些规范，提供了一些编程的API接口，但具体实现规则由 ORM 厂商提供实现。

### persistence.xml

JPA规范要求在类路径的 META_INF 目录下放置 persistence.xml 文件，**文件名是固定的**

### JPA基本基础

```java
    @Id//主键标识映射主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成方式
	@Column(name = "id")//数据库列名 如果和属性一样的话就可以不写
	@Entity//标明这是一个持久化类
	@Table(name = "jpa_coutomer")//表示数据库中的表名
    /**
     * –IDENTITY：采用数据库ID自增长的方式来自增主键字段，Oracle 不支持这种方式； 
     * –AUTO： JPA自动选择合适的策略，是默认选项； 
     * –SEQUENCE：通过序列产生主键，通过@SequenceGenerator 注解指定序列名，MySql不支持这种方式 
     * –TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植。
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成方式
    @Basic//表示一个简单的属性到数据库表的映射，对与没有任何标注的 getXxx方法，默认为@Basic
    /**
     * name 列明
     * nullable 非空约束 false 不为null true 可为null
     * unique 唯一约束
     * length 长度约束
     */
    @Column(name = "email",nullable = true,	length = 122 )
    @Transient//，不需要映射为数据库的一列可加上此注解 加此注解可以屏蔽    @Basic
        /**
     * 主要用来指定时间的类型
     * DATE 精确到天
     * DATETIME 精确到时间分秒
     * TIMESTAMP 时间卓
     */
    @Temporal(value = TemporalType.TIMESTAMP)//时间戳
```
### 主键生成策略

<img src="(https://img-blog.csdnimg.cn/20200920180313402.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxNzc5MjQ3MjU3,size_16,color_FFFFFF,t_70#pic_center">

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200920180313402.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxNzc5MjQ3MjU3,size_16,color_FFFFFF,t_70#pic_center)

```java
    @Id//主键标识
    @TableGenerator(name = "ID_GENERATOR",
                    table = "jpa_id",//表名
                    pkColumnName = "PK_NAME",
            pkColumnValue = "CUSTOMER_ID",
            valueColumnName = "PK_VALUE",
            allocationSize = 100//每次张100
    )
    /**
     * –IDENTITY：采用数据库ID自增长的方式来自增主键字段，Oracle 不支持这种方式；
     * –AUTO： JPA自动选择合适的策略，是默认选项；
     * –SEQUENCE：通过序列产生主键，通过@SequenceGenerator 注解指定序列名，MySql不支持这种方式
     * –TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植。
     */
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "ID_GENERATOR")//主键生成方式
//    @Column(name = "id")//列名如果和属性一样的话就可以不写
    private Integer id;
```



### JPA关系映射

#### 单向多对1

 例如：一个用户多个订单

订单类：

```java
@Entity
@Table(name = "jpa_orders")
public class Order {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "order_name")
    private String orderName;

    @JoinColumn(name = "customer_id")//映射外键
    @ManyToOne//映射多对一的关联关系
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
```

用户类

```
@Table(name = "jpa_coutomer")//表示数据库中的表名
@Entity//标明这是一个持久化类
public class Customer {
    @Id//主键标识
    @GeneratedValue(strategy = GenerationType.AUTO)//主键生成方式
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

```



#### 测试代码示例

```java
    /**
     * 单向多对一
     *  一个用户多个订单
     *
     *  建议：先增加单个的一方 再增加 多个一方 ， 这样 jpa可以少执行一个 update 语句
     */
    @Test
    public void testManyToOne(){

        //建立关联关系
        Customer customer = new Customer("多对1", "779247267@163.com", 18);
        Order order = new Order("订单名称", customer);

        //执行保存操作
        entityManager.persist(customer);
        entityManager.persist(order);
    }

    /**
     * 查询嵌套的单项多对一   对象时候，默认使用 左连接进行查询
     */
    @Test
    public void testFindManyToOne(){
        Order order = entityManager.find(Order.class, 13);
//        System.out.println(order.getCustomer());
    }

    /**
     * 删除时候 不能直接删除1的一方，因为有外键约束 会报错
     */
    @Test
    public void testManyToOneRemove(){
        Customer customer = entityManager.find(Customer.class, 14);
        entityManager.remove(customer);
    }

    /**
     * 多的一方修改 一的一方
     */
    @Test
    public void testManyToOneUpdate(){
        Order order = entityManager.find(Order.class, 13);
        order.getCustomer().setAge(77);
    }
```

#### 单向一（用户）对多（订单）

在用户对象的属性上加

```java
    @JoinColumn(name = "coutomer_one_id")//一对多的关联外键
//    @OneToMany(fetch = FetchType.EAGER)//一对多映射的时候默认使用懒加载
    @OneToMany(cascade = {CascadeType.REMOVE})
    private Set<OrderOne> orderOnes = new HashSet<>();
```

#### 测试示例代码

```java
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
```