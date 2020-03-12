package 设计模式.建造者模式.食物;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 14:08
 * @description: 食物 汉堡的抽象类
 */
public abstract class Burger implements Item {


    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
