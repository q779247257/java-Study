# Spring 事务管理

## 1.事务问题

### 1.导入JAR包

```
<dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.0.2.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.0.2.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>5.0.2.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.6</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>5.0.2.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.8.7</version>
        </dependency>

        <!--数据库连接 -->
        <dependency>
            <groupId>commons-dbutils</groupId>
            <artifactId>commons-dbutils</artifactId>
            <version>1.4</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.6</version>
        </dependency>

        <dependency>
            <groupId>c3p0</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.1.2</version>
        </dependency>

    </dependencies>
```

### 2.Dao层

#### 2.1接口层

```
public interface AccountDao {

    /**
     * 加钱方法
     * @param id
     * @param money
     */
    void increaseMoney(Integer id , Double money);

    /**
     * 减钱方法
     * @param id
     * @param money
     */
    void decreaseMoney(Integer id , Double money);
}
```



#### 2.2实现层

```
@Component("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void increaseMoney(Integer id, Double money) {
        jdbcTemplate.update("update ar_account set money = money + ? where id = ?",money,id);
    }

    public void decreaseMoney(Integer id, Double money) {
        jdbcTemplate.update("update ar_account set money = money - ? where id = ?",money,id);
    }
}

```



### 3.Service层

#### 3.1 service接口层

```

public interface AccountService {
	//转账的方法
    void Transfer(Integer from,Integer to ,Double money);
}

```



#### 3.2 service实现层

```
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    public void Transfer(final Integer from, final Integer to,final Double money) {
                accountDao.decreaseMoney(from,money);
                int i =1/0;
                accountDao.increaseMoney(to,money);
    }
}
```

### 4.配置xml

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-4.2.xsd
http://www.springframework.org/schema/context
http://www.springframework.org/schema/context/spring-context-4.2.xsd
http://www.springframework.org/schema/aop
http://www.springframework.org/schema/aop/spring-aop-4.2.xsd
http://www.springframework.org/schema/tx
http://www.springframework.org/schema/tx/spring-tx-4.2.xsd">

   <context:component-scan base-package="com.qf"></context:component-scan>

    <!-- 配置数据源 -->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <!--连接数据库的必备信息-->
        <property name="driverClass" value="com.mysql.jdbc.Driver"></property>
        <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/test"></property>
        <property name="user" value="root"></property>
        <property name="password" value="59852369"></property>
    </bean>
    <bean id="jdbcTemplate"  class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"/>
    </bean>
    </beans>
```



### 5.调用

```
@RunWith(SpringJUnit4ClassRunner.class)
//指定创建容器时使用哪个配置文件
@ContextConfiguration("classpath:applicationContext.xml")
public class TestJdbc02 {

    @Autowired
    private AccountService accountService;

    @Test
    public void test(){
        accountService.Transfer(3,2,1000.0);
    }
}
```

## 2.使用AOP配置事务的方式

#### 2.1 更改xml配置，配置事务，开启AOP的注解模式

```
  <!-- 平台事务管理器 -->
 <bean id="transactionManager" 		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
```

#### 2.2配置通知类

```
@Component
@Aspect
public class MyAdvice {

    @Autowired
    private DataSourceTransactionManager transactional;

    @Pointcut("execution( * com.qf.service.*.*.*(..))")
    public void pt1(){

    }

    @Around("pt1()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactional.getTransaction(def);
        Object proceed=null;
        try {
            System.out.println("这是环绕通知之前的部分");
            proceed = point.proceed();
            System.out.println("这是环绕通知之后的部分");
            transactional.commit(status);
        }catch (Exception e){
            transactional.rollback(status);
            System.out.println(e.getMessage());
            System.out.println("这是环绕通知异常的部分");
        }finally {
            System.out.println("这是最终通知");
        }
        return proceed;
    }
}

```



## 3.使用TransactionTemplate配置事务的方式

#### 3.1 增加xml配置

```
<bean id="template" class="org.springframework.transaction.support.TransactionTemplate">
        <property name="transactionManager" ref="transactionManager" />
  </bean>
     <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <!-- 传播行为 -->
            <!-- REQUIRED：如果有事务，则在事务中执行；如果没有事务，则开启一个新的事物 -->
            <tx:method name="save*" propagation="REQUIRED" />
            <tx:method name="insert*" propagation="REQUIRED" />
            <tx:method name="add*" propagation="REQUIRED" />
            <tx:method name="create*" propagation="REQUIRED" />
            <tx:method name="delete*" propagation="REQUIRED" />
            <tx:method name="update*" propagation="REQUIRED" />
            <tx:method name="transfer" propagation="REQUIRED" />
            <!-- SUPPORTS：如果有事务，则在事务中执行；如果没有事务，则不会开启事物 -->
            <tx:method name="find*" propagation="SUPPORTS" read-only="true" />
            <tx:method name="select*" propagation="SUPPORTS" read-only="true" />
            <tx:method name="get*" propagation="SUPPORTS" read-only="true" />
        </tx:attributes>
    </tx:advice>

    <aop:config>
        <aop:pointcut id="txPointCut" expression="execution(* com.qf.service.*.*(..))" />
        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointCut"/>
    </aop:config>
```

#### 3.2修改service转账方法

```
    //将事务管理类注入到service中
    @Autowired
    private TransactionTemplate template;

    public void Transfer(final Integer from, final Integer to,final Double money) {
    template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                accountDao.decreaseMoney(from,money);
                int i =1/0;
                accountDao.increaseMoney(to,money);
            }
        });

    }
```



## 4.使用注解配置事务方式

#### 4.1修改xml

需要在Spring的配置中通过一行配置'通知'Spring容器对标注@Transactional注解的Bean进行加工处理!

在默认情况, <tx:annotation-driven  /> 中transaction-manager属性会自动使用名为 "transactionManager" 的事务管理器.所以,如果用户将事务管理器的id定义为 `transactionManager` , 则可以进一步将①处的配置简化为 <tx:annotation-driven />.

**使用以上测试用例即可**

```
    <!--开启spring对注解AOP的支持-->
    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
     <!--①配置注解事务开启 对标注@Transactional注解的Bean进行加工处理,以织入事物管理切面 -->
      <tx:annotation-driven transaction-manager="transactionManager" />
```

#### 4.2修改service代码，配置事务管理

因为注解本身具有一组默认的事务属性,所以往往只要在需要事务的业务类中添加一个@Transactional注解,就完成了业务类事务属性的配置!

```
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    public void Transfer(final Integer from, final Integer to,final Double money) {
                accountDao.decreaseMoney(from,money);
                int i =1/0;
                accountDao.increaseMoney(to,money);
    }
}
```

#### 4.3在何处标注@Transactional注解

@Transactional注解可以直接用于接口定义和接口方法,类定义和类的public方法上.

但Spring建议在业务实现类上使用@Transactional注解,当然也可以添加到业务接口上,但是这样会留下一些容易被忽视的隐患,因为注解不能被继承,所以业务接口中标注的@Transactional注解不会被业务类实现继承.

方法出添加注解会覆盖类定义处的注解,如果有写方法需要使用特殊的事务属性,则可以在类注解的基础上提供方法注解,如下:

```
@Transactional
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    //不等于默认值!可以覆盖类注解
    @Transactional(readOnly = false , isolation = Isolation.READ_COMMITTED)
    public void transfer(final Integer from, final  Integer to, final  Double money) {

         accountDao.decreaseMoney(from,money);

        // int i = 1/0;

         accountDao.increaseMoney(to,money);

    }
}
```

