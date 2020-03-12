package 设计模式.建造者模式.食物;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 15:01
 * @description: 百事可乐  饮品扩展类
 */
public class Pepsi extends ColdDrink {
    @Override
    public String name() {
        return "百事可乐";
    }

    @Override
    public float price() {
        return 35.0f;
    }
}
