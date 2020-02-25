# Spring-AOP的使用（xml配置）

## 1.导入对应的AOP jar 包，需注意spring JAR版本需相同

```
	    <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>5.0.2.RELEASE</version>
        </dependency>
 
        <dependency>
            <groupId>aopalliance</groupId>
            <artifactId>aopalliance</artifactId>
            <version>1.0</version>
        </dependency>

        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.8.7</version>
        </dependency>
```

## 2.创建通知类

```
 //前置
    public void before(){
        System.out.println("这是前置通知");
    }
    //后置
    public void after(){
        System.out.println("这是后置通知");
    }
    //异常通知
    public void throwing(){
        System.out.println("这是异常通知");
    }
    //最终通知
    public void afterEnd(){
        System.out.println("这是最终通知");
    }
	//环绕通知
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("这是环绕通知之前的部分");
        Object proceed=null;
        try {
            proceed = point.proceed();
            System.out.println("这是环绕通知之后的部分");
        }catch (Exception e){
            System.out.println("这是环绕通知异常的部分");
        }finally {
            System.out.println("这是最终通知");
        }


        return proceed;
    }
```

## 3.配置xml文件

### 3.1 导入约束

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans-4.2.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context-4.2.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop-4.2.xsd ">
       </bean>
```

### 3.2配置扫描包以及将我们的通知类交给spring管理

```
<context:component-scan base-package="com.qf"></context:component-scan>

 <bean id="myAdvice" class="com.qf.handle.MyAdvice"></bean>
```

### 3.3开始AOP的配置

#### 3.3.1使用aop:config标签表明开始AOP的配置

```
<aop:config>
</aop:config>
```

#### 3.3.2 使用aop:aspect标签表明配置切面，id属性：是给切面提供一个唯一标识，ref属性：是指定通知类bean的Id。

```
<aop:aspect id="advice" ref="myAdvice">
</aop:aspect>
```

#### 3.3.3 配置切入点表达式



```
  切入点表达式的写法：
                 关键字：execution(表达式)
                 表达式：
                     访问修饰符  返回值  包名.包名.包名...类名.方法名(参数列表)
                 标准的表达式写法：
                     public void com.qf.service.impl.BookServiceImpl.save()
                 访问修饰符可以省略
                     void com.qf.service.impl.BookServiceImpl.save()
                 返回值可以使用通配符，表示任意返回值
                     * com.qf.service.impl.BookServiceImpl.save()
                 包名可以使用通配符，表示任意包。但是有几级包，就需要写几个*.
                     * *.*.*.BookServiceImpl.save())
                 包名可以使用..表示当前包及其子包
                     * *..BookServiceImpl.save()
                 类名和方法名都可以使用*来实现通配
                     * *..*.*()
                 参数列表：
                     可以直接写数据类型：
                         基本类型直接写名称           int
                         引用类型写包名.类名的方式   java.lang.String
                     可以使用通配符表示任意类型，但是必须有参数
                     可以使用..表示有无参数均可，有参数可以是任意类型
                 全通配写法：
                     * *..*.*(..)

                 实际开发中切入点表达式的通常写法：
                 切到业务层实现类下的所有方法
                 * com.qf.service.impl.*.*(..)
 
 <aop:pointcut id="ponitcut" expression="execution( * com.qf.service.*.*.*(..))"></aop:pointcut>
```



#### 3.3.4 在aop:aspect标签的内部使用对应标签来配置通知的类型

```
 <aop:before method="before" pointcut-ref="ponitcut"></aop:before>
 <aop:after-returning method="after" pointcut-ref="ponitcut"></aop:after-returning>
 <aop:after-throwing method="throwing" pointcut-ref="ponitcut"></aop:after-throwing>
 <aop:after method="afterEnd" pointcut-ref="ponitcut"></aop:after>
 //环绕通知
  <aop:around method="around" pointcut-ref="ponitcut"></aop:around>
```

# Spring-aop的使用（注解）

## 1.导入jar包，跟xml方式导入的一样，需注意spring Jar版本需相同

## 2.创建通知类,与xml相同，不同的是

```
@Component //将该类交给spring
@Aspect		//标注该类是一个切面类
public class MyAdviceAnnoation {

    @Pointcut("execution( * com.qf.service.*.*.*(..))") //切入点表达式
    public void pt1(){

    }
    //前置
  //  @Before("pt1()")
    public void before(){
        System.out.println("这是前置通知");
    }
    //后置
   // @AfterReturning("pt1()")
    public void after(){
        System.out.println("这是后置通知");
    }
    //异常通知
   // @AfterThrowing("pt1()")
    public void throwing(){
        System.out.println("这是异常通知");
    }
    //最终通知
    //@After("pt1()")
    public void afterEnd(){
        System.out.println("这是最终通知");
    }
    @Around("pt1()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("这是环绕通知之前的部分");
        Object proceed=null;
        try {
            proceed = point.proceed();
            System.out.println("这是环绕通知之后的部分");
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("这是环绕通知异常的部分");
        }finally {
            System.out.println("这是最终通知");
        }
        return proceed;
    }
}

```

## 3.开启扫描以及spring-aop的注解模式，约束跟我们xml配置的相同

```
 <context:component-scan base-package="com.qf"></context:component-scan>
 <!-- 开始spring-aop注解模式-->
  <aop:aspectj-autoproxy ></aop:aspectj-autoproxy>
```

