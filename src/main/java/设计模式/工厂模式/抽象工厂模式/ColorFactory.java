package 设计模式.工厂模式.抽象工厂模式;

import 设计模式.工厂模式.抽象工厂模式.形状.Shape;
import 设计模式.工厂模式.抽象工厂模式.颜色.Blue;
import 设计模式.工厂模式.抽象工厂模式.颜色.Color;
import 设计模式.工厂模式.抽象工厂模式.颜色.Green;
import 设计模式.工厂模式.抽象工厂模式.颜色.Red;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 10:32
 * @description:
 */
public class ColorFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType){
        return null;
    }

    @Override
    public Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }
}