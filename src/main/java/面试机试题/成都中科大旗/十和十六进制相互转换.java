package 面试机试题.成都中科大旗;

import java.math.BigInteger;

/**
 * @Author: 轩轩
 * @Date: 2021/2/23 21:52
 * @description: 成都中科大旗 机试题  10和16进制相互转换
 */
public class 十和十六进制相互转换 {

    public static void main(String[] args) {
        int numb=9999;
        String hex=	encodeHEX(numb);
        System.out.println("  9999  的16进制为"+hex);
        System.out.println("    16進制字符 "+hex+" 的10進制數字為   "+decodeHEX(hex));
    }

    //10转16
    public static String encodeHEX(Integer numb){
        String hex= Integer.toHexString(numb);
        return hex;

    }

    //16转10
    public static int decodeHEX(String hexs){
        BigInteger bigint=new BigInteger(hexs, 16);
        int numb=bigint.intValue();
        return numb;
    }


}
