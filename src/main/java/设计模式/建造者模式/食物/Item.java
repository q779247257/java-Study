package 设计模式.建造者模式.食物;

/**
 * @Author: 轩轩
 * @Date: 2020/3/12 13:47
 * @description: 食物条目
 */
public interface Item {
    /**
     * 名字
     */
    public String name();

    /**
     * 食物包装
     */
    public Packing packing();

    /**
     * 价格
     */
    public float price();//价格
}
