package 设计模式.建造者模式;

import 设计模式.建造者模式.食物.ChickenBurger;
import 设计模式.建造者模式.食物.Coke;
import 设计模式.建造者模式.食物.Pepsi;
import 设计模式.建造者模式.食物.VegBurrger;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 15:10
 * @description: 负责创建Meal对象
 */
public class MealBuilder {

    /**
     * 准备 素食套餐  蔬菜汉堡+咖啡
     * @return 套餐
     */
    public Meal prepareVegMeal(){
        Meal meal = new Meal();
        meal.addItem(new VegBurrger());
        meal.addItem(new Coke());
        return meal;
    }

    /**
     * 准备 肉食套餐   可乐+汉堡
     * @return 套餐
     */
    public Meal prepareNonVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
