package 面试机试题.成都中科大旗;

/**
 * @Author: 轩轩
 * @Date: 2021/2/23 22:14
 * @description:
 */
public class 单例模式 {
    public static void main(String[] args) {

        SingleDemo pojo = SingleDemo.getPojo();
        SingleDemo pojo1 = SingleDemo.getPojo();
        SingleDemo pojo2 = SingleDemo.getPojo();
        System.out.println(pojo == pojo1);
        System.out.println(pojo == pojo2);
        System.out.println(pojo1 == pojo2);

    }
}


class SingleDemo{
    private static SingleDemo bean = null;
    private SingleDemo(){

    }


    public static synchronized  SingleDemo getPojo(){
        if (bean == null){
            bean = new SingleDemo();
        }
        return bean;
    }
}
