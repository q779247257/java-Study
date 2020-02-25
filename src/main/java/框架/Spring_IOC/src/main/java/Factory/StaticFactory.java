package Factory;

import domain.User;

/**
 * @Author: 轩轩
 * @Date: 2020/2/22 15:11
 * @description: 工厂类
 */
public class StaticFactory {
    public static User getStaticFactory(){
        System.out.println("静态工厂被引用了");
        return new User();
    }
}
