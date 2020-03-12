package 设计模式.建造者模式.食物;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 13:56
 * @description: 食物包装实现类 - 瓶子
 */
public class Bottle implements Packing {
    @Override
    public String pack() {
        return "瓶子";
    }
}
