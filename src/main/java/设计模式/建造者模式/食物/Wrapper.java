package 设计模式.建造者模式.食物;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 13:51
 * @description: 食物的包装实现类，包装
 */
public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "包装";
    }
}
