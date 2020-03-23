package com.xuan.uitls;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @Author: 轩轩
 * @Date: 2020/3/23 11:34
 * @description: lambda 表达式的工具类
 */
public class LambdaUtils {
    /**
     * 用于获取遍历的下标和集合中的对象
     */
    public static <T> Consumer<T> consumerWithIndex(BiConsumer<T, Integer> consumer) {
        class Obj {
            int i;
        }
        Obj obj = new Obj();
        return t -> {
            int index = obj.i++;
            consumer.accept(t, index);
        };
    }

}
