package 算法.简单;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/5/21
 * @描述：
 *  罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 *
 *  字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，
 * 例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 *
 * 示例:
 *
 * 输入: "III"
 * 输出: 3
 *
 * 输入: "IV"
 * 输出: 4
 *
 * 输入: "IX"
 * 输出: 9
 *
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 *
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */
public class 罗马数字转整数 {
    private static String testString = "MCMXCIV";

    public static void main(String[] args) {
        int i = romanToInt(testString);
        System.out.println("info:"+i);
    }

    public static int romanToInt(String s) {
        char[] chars = s.toCharArray();

        int resutInt = 0;//返回的数字
        int compare = 0;//比较的数字

        for (int i= 0 ; i<chars.length ; i++){
            if (i>0){
                //获取当前的数字(右边)
                compare = getIntByInt(chars[i]);
                //判断当前数字大于上一个数字
                int itemNumber  = getIntByInt(chars[i - 1]);
                if (compare >itemNumber){
                    resutInt  = resutInt + (compare - itemNumber);
                    resutInt  = resutInt - itemNumber;
                    continue;
                }
            }
            resutInt += getIntByInt(chars[i]);
        }
    return resutInt;
    }


    public static int getIntByInt(char v){
        int resut = 0;
        switch (v){
            case 'I' : resut = 1 ; break;
            case 'V' : resut = 5 ; break;
            case 'X' : resut = 10 ; break;
            case 'L' : resut = 50 ; break;
            case 'C' : resut = 100 ; break;
            case 'D' : resut = 500 ; break;
            case 'M' : resut = 1000 ; break;
            default:
                throw new IllegalStateException("Unexpected value: " + v);
        }
        return resut;
    }
}
