package 设计模式.建造者模式.食物;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 14:59
 * @description: 咖啡  饮品扩展类
 */
public class Coke extends ColdDrink {
    @Override
    public String name() {
        return "咖啡饮品";
    }

    @Override
    public float price() {
        return 50.0f;
    }
}
