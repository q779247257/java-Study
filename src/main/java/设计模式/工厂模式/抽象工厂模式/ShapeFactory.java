package 设计模式.工厂模式.抽象工厂模式;

import 设计模式.工厂模式.抽象工厂模式.AbstractFactory;
import 设计模式.工厂模式.抽象工厂模式.形状.Circle;
import 设计模式.工厂模式.抽象工厂模式.形状.Rectangle;
import 设计模式.工厂模式.抽象工厂模式.形状.Shape;
import 设计模式.工厂模式.抽象工厂模式.形状.Square;
import 设计模式.工厂模式.抽象工厂模式.颜色.Color;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 10:25
 * @description: 形状工厂类 基于给定的信息形成实体类
 */
public class ShapeFactory extends AbstractFactory {
    @Override
    public Color getColor(String colorType) {
        return null;
    }

    @Override
    public Shape getShape(String shapeType) {
        //圆形
        if (shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        }
        //矩形
        if (shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        }
        //正方形
        if (shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }
}
