package 设计模式.建造者模式.食物;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 14:58
 * @description: 鸡肉汉堡  -- 汉堡扩展类
 */
public class ChickenBurger extends Burger{
    @Override
    public String name() {
        return "鸡肉汉堡";
    }

    @Override
    public float price() {
        return 50.5f;
    }
}
