package 算法;

/**
 * @Author: 轩轩
 * @Date: 2020/3/25 13:47
 * @description:
 */
public class AlgorithmCode {
    public static void main(String[] args) {
        int reverse = new AlgorithmCode().reverse(1534236469);
        System.out.println("info:"+
                        (-123 / 10)
                );
        System.out.println("结果："+reverse);
    }
    //todo 整数反转 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
    public int reverse(int number) {
        long resultNumber = 0;
        while (number != 0){
            //判断数据溢出
            if ( resultNumber >  Integer.MAX_VALUE /10 || (long)resultNumber < Integer.MIN_VALUE /10) return 0;
            //取得最后一位数
           int add =  number % 10;
           //每次循环乘10 加上最后一位数
           resultNumber = resultNumber * 10 + add;
            //参数减少一位数
            number /= 10;
            //判断数据是否溢出
        }
        return (int) resultNumber;
    }
}
