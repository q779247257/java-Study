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
    @Column(name = "email",nullable = true,length = 122 )
    @Transient//，不需要映射为数据库的一列可加上此注解 加此注解可以屏蔽    @Basic
        /**
     * 主要用来指定时间的类型
     * DATE 精确到天
     * DATETIME 精确到时间分秒
     * TIMESTAMP 时间卓
     */
    @Temporal(value = TemporalType.TIMESTAMP)//时间戳
```