package 设计模式.工厂模式.抽象工厂模式.颜色;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 10:17
 * @description: 绿色
 */
public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("绿色的 fill() 方法");
    }
}
