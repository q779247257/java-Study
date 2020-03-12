package 设计模式.工厂模式.抽象工厂模式;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 10:38
 * @description: 工厂生成器，用于生成不同的工厂
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String choice){
        //获取形状工厂
        if(choice.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        } else
            //获取颜色工厂
            if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
    }
}
