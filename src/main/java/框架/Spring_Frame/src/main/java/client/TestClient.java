package client;

import domain.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
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
     */
    @Test
    public void getSpringBeanPojoTest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //getBean 可以使用配置文件中的id值,也可以使用配置文件的name值.
        User user = (User) applicationContext.getBean("user");
        User userClass = applicationContext.getBean("userName",User.class);

        System.out.println("user = " + user);
        System.out.println("userClass = " + userClass);
    }
}
