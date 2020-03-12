package 设计模式.建造者模式;

import 设计模式.建造者模式.食物.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 15:04
 * @description: 一顿饭 ， 带有自定义的Item对象
 */
public class Meal {
    private List<Item> items = new ArrayList<>();

    /**
     * 添加食物
     * @param item 食物
     */
    public void addItem(Item item){
        items.add(item);
    }

    /**
     * 获取点的食物总价格
     * @return 总价格
     */
    public float getCost(){
        float cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    /**
     * 展示食物的名字及包装和价格等
     */
    public void showItems(){
        for (Item item : items) {
            System.out.print("食物的名字 : "+item.name());
            System.out.print(", 食物的包装 : "+item.packing().pack());
            System.out.print(", 食物的价格 : "+item.price()+"\n");
        }
    }
}
