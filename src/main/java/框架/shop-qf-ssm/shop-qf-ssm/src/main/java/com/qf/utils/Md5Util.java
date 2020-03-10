package com.qf.utils;


import com.mchange.lang.ByteUtils;

import java.security.MessageDigest;

/**
 * @Author: 轩轩
 * @Date: 2020/3/6 16:55
 * @description:
 */
public class Md5Util {

    /**
     * md5加密
     * @param text 加密的字符串
     * @return 加密后的字符串
     */
    public static String encryption(String text)  {
        byte[] digest = new byte[0];
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            digest = md5.digest(text.getBytes("utf-8"));
        } catch (Exception e) {
            System.out.println("MD5加密错误");
            e.printStackTrace();
        }
        String resultMd5Value = ByteUtils.toHexAscii(digest);
        // 加密后的字符串
        return resultMd5Value;
    }
}






