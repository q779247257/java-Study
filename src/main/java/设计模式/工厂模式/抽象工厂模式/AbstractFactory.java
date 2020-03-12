package 设计模式.工厂模式.抽象工厂模式;

import 设计模式.工厂模式.抽象工厂模式.形状.Shape;
import 设计模式.工厂模式.抽象工厂模式.颜色.Color;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 10:19
 * @description: 抽象工厂，用于获取 颜色 和 形状
 */
public abstract class AbstractFactory {
    //获取颜色工厂
    public abstract Color getColor(String colorType);

    //获取形状工厂
    public abstract Shape getShape(String shapeType);
}
