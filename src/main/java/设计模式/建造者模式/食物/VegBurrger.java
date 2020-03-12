package 设计模式.建造者模式.食物;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 14:56
 * @description: 蔬菜汉堡 扩展类
 */
public class VegBurrger extends Burger{
    @Override
    public String name() {
        return "蔬菜汉堡";
    }

    @Override
    public float price() {
        return 25.0f;
    }
}
