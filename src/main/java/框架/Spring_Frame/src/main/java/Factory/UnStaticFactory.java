package Factory;

import domain.User;

/**
 * @Author: 轩轩
 * @Date: 2020/2/22 15:22
 * @description: 非静态工厂类
 */
public class UnStaticFactory {
    public User getFactoryUser(){
        System.out.println("非静态工厂被引用了");
        return new User();
    }
}