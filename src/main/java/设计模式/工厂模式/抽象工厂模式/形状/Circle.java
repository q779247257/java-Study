package 设计模式.工厂模式.抽象工厂模式.形状;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 10:11
 * @description: 圆圈
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("圆形的 draw() 方法");
    }
}
