package 设计模式.工厂模式.抽象工厂模式;

import 设计模式.工厂模式.抽象工厂模式.形状.Shape;
import 设计模式.工厂模式.抽象工厂模式.颜色.Color;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 10:50
 * @description:
 */
public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        //获取形状工厂
        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");
        //获取 圆形对象
        Shape circle = shapeFactory.getShape("CIRCLE");
        circle.draw();

        ///获取矩形对象
        Shape rectangle = shapeFactory.getShape("RECTANGLE");
        circle.draw();

        //获取颜色工厂
        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");

        //获取红色对象
        Color red = colorFactory.getColor("RED");
        red.fill();

        //获取绿色对象
        Color green = colorFactory.getColor("GREEN");
        green.fill();
    }
}
