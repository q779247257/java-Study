package 设计模式.工厂模式.抽象工厂模式.形状;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 10:09
 * @description: 矩形 实现 形状 接口
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("矩形的 draw() 方法ss");
    }
}
