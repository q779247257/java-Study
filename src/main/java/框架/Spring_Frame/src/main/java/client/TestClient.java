package client;

import domain.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: 轩轩
 * @Date: 2020/2/22 14:29
 * @description:
 */
public class TestClient {

    /**
     * 通过自己new 来创建对象
     */
    @Test
    public void getBeanTest(){
        User user = new User();
        System.out.println("new is :"+ user);
    }

    /**
     * 通过Spring 的Bean注入来获取对象
     * 测试IOC
     * 输出结果：
     *          对象初始化的方法2020-2-22 14:55:44
     *          user = User{name='null', age='null', sex='null'}
     *          userClass = User{name='null', age='null', sex='null'}
     *          info:true
     *          对象被销毁了
     *
     *          注：
     *          scope：prototype 设置为创建对象为非单例的时候，容器被关闭的时候不会销毁对象
     */
    @Test
    public void getSpringBeanPojoTest(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //getBean 可以使用配置文件中的id值,也可以使用配置文件的name值.
        User user = (User) applicationContext.getBean("user");
        User userClass = applicationContext.getBean("userName",User.class);

        System.out.println("user = " + user);
        System.out.println("userClass = " + userClass);

        //true Spring 创建对象默认是单例的
        //配置bean的 scope="prototype" 的时候 创建的对象不是单例的
        System.out.println("info:"+ (user == userClass) );
        //关掉容器，销毁对象，厨房对象销毁方法
        applicationContext.close();
    }

    /**
     * 静态工厂类获取Bean对象
     * 输出结果：
     *      对象被引用了
     *      对象初始化的方法2020-2-22 15:18:12
     *      静态工厂被引用了
     *      对象被引用了
     */
    @Test
    public void getPojoByStaticFactoryTest(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //getBean 可以使用配置文件中的id值,也可以使用配置文件的name值.
        User factoryName = applicationContext.getBean("factoryId", User.class);

    }
}
