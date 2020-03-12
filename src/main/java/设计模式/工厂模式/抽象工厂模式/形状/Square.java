package 设计模式.工厂模式.抽象工厂模式.形状;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 10:10
 * @description: 正方形 实现 形状接口哦
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("正方形的 draw() 方法");
    }
}
