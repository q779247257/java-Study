package client;

import domain.User;
import domain.UserDetail;
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
        User factoryName = applicationContext.getBean("staticFactoryId", User.class);

    }

    /**
     * 非静态工厂类获取Bean对象实例
     * 输出结果：
            对象被引用了
            对象初始化的方法2020-2-22 15:33:18
            静态工厂被引用了
            对象被引用了
            非静态工厂被引用了
            对象被引用了
     */
    @Test
    public void getPojoByUnStaticFactoryTest(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //getBean 可以使用配置文件中的id值,也可以使用配置文件的name值.
        User factoryName = applicationContext.getBean("unStaticFactoryId", User.class);
    }

    /**
     * 通过有参构造注入创建对象示例
     * 打印结果：
     *      user = User{name='轩轩', age='18', sex='男'}
     */
    @Test
    public void getPojoByParamConstructor(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //getBean 可以使用配置文件中的id值,也可以使用配置文件的name值.
        User user = applicationContext.getBean("userConstructorId", User.class);
        System.out.println("user = " + user);

    }
    /**
     * set方法注入对象的属性值
     * 普通属性直接只用value注入即可
     * 打印结果：
     *          user = User{name='轩轩', age='18', sex='男'}
     */
    @Test
    public void getPojoBySet(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //getBean 可以使用配置文件中的id值,也可以使用配置文件的name值.
        User user = applicationContext.getBean("userSetId", User.class);
        System.out.println("user = " + user);
    }

    /**
     * set方法注入引用类型的属性值 使用ref 引用
     * 打印结果：
     *          user = User {
     * 	            name = '轩轩', age = '18', sex = '男', userLogin = UserLogin {
     * 		            userLogin = '我的账户', userPassword = '我的密码'
     *        }
     * }
     */
    @Test
    public void getPojoUserAndLogin(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //getBean 可以使用配置文件中的id值,也可以使用配置文件的name值.
        User user = applicationContext.getBean("userAndUserLoginId", User.class);
        System.out.println("user = " + user);
    }

    /**
     * Sprinbg 注入复杂类型 示例
     *      List： <list><list/>标签
     *      int[]： <array></array>标签
     *      Map： <map> map标签
     *      Set: <set>庙前
     *      Properties
     *
     *  打印结果：
     *  user =  UserDetail{myList=[1, 2, 3],
     *          myArray=[1, 2, 3],
     *          myMap={name=轩轩,
     *          age=年龄, sex=男},
     *          mySet=[1, 2, 3],
     *          myPro={age=18, name=轩轩}}
     */
    @Test
    public void getPojoComplexType(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //getBean 可以使用配置文件中的id值,也可以使用配置文件的name值.
        UserDetail user = applicationContext.getBean("userDetailId", UserDetail.class);
        System.out.println("user = " + user);
    }
}
