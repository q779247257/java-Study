package 设计模式.工厂模式.抽象工厂模式.颜色;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 10:16
 * @description: 红色
 */
public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("红色的 fill（） 方法");
    }
}
