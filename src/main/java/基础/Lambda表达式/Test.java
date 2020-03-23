package 基础.Lambda表达式;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 轩轩
 * @Date: 2019/12/28 19:02
 * @description:
 */
public class Test {
    public static void main(String[] args) {
//        LamvdaTest number = () -> {return 2;};
//        LamvdaTest number2 = () -> 3;
        LamvdaTest number2 = (a,b,c) -> a+b+c;

        System.out.println(number2.sum2(2,2,2));
    }

    /**
     * steam流遍历 或者 foreach中获取元素和下标
     */
    @org.junit.Test
    public void test001(){
        List<String> list = new ArrayList<>();
        //在集合中添加元素
        for ( int i = 0 ; i<=200 ; i++){
            list.add(""+i);
        }
        list.stream().forEach(LambdaUtils.consumerWithIndex((item , index) -> {
            System.out.println("下标为"+index);
        }));
        System.out.println("list的长度为:"+list.size());
        System.out.println("info:"+list.get(list.size()-1));
    }
}
