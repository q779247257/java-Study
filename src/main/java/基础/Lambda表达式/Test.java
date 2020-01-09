package 基础.Lambda表达式;

/**
 * @Author: 轩轩
 * @Date: 2019/12/28 19:02
 * @description:
 */
public class Test {
    public static void main(String[] args) {
        LamvdaTest number = () -> {return 2;};
        LamvdaTest number2 = () -> 3;

        System.out.println(number2.sum());
    }
}
