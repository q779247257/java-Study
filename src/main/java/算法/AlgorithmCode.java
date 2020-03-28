package 算法;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author: 轩轩
 * @Date: 2020/3/25 13:47
 * @description:
 */
public class AlgorithmCode {
    public static void main(String[] args) {
        int aabbcc = new AlgorithmCode().reverse("abcabcbb".length());
        System.out.println("结果"+aabbcc);

    }
    //todo 整数反转 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
    public int reverse(int number) {
        long resultNumber = 0;
        while (number != 0){
            //判断数据溢出
            if ( resultNumber >  Integer.MAX_VALUE /10 || (long)resultNumber < Integer.MIN_VALUE /10) {
                System.out.println("反转之后，超出了int的范围");
                return 0;
            }
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

    // todo 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。(回文数算法)
    /**
     * 输入: -121
     * 输出: false
     * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     *
     * 输入: 121
     * 输出: true
     * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     */
    public boolean isPalindrome(int number) {
        int sum = number;
        int resultNumber = 0;
        while (number > 0){
            //  每次取最后一位数
            int add =  number % 10;
            //当数*10（进一位）加上读取的最后一位数
            resultNumber = resultNumber * 10 + add;
            //往后推一位数
            number =  number / 10;
        }
        System.out.println("sum:"+sum);
        System.out.println("resultNumber:"+resultNumber);
        return resultNumber == sum;
    }





}
