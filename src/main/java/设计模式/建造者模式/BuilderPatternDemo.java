package 设计模式.建造者模式;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 15:14
 * @description: 建造者模式 演示
 */
public class BuilderPatternDemo {

    private static MealBuilder mealBuilder = new MealBuilder();

    public static void main(String[] args) {

        Meal nonVegMealmeal = mealBuilder.prepareNonVegMeal();
        System.out.println("准备肉食套餐--------------------------------------------------------------------");
        nonVegMealmeal.showItems();
        System.out.println("总价格 " +nonVegMealmeal.getCost());

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("准备素食套餐-------------------------------------------------------------------");
        vegMeal.showItems();
        System.out.println("总价格 " +vegMeal.getCost());
    }

}
