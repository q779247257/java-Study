package com.xuan;

import com.xuan.protobuf_pojo.DemoProto;
import org.junit.Test;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/5/28
 * @描述：
 */
public class demoProtobufTest {
    public static void main(String[] args) {
        DemoProto.Clazz.Builder builder = DemoProto.Clazz.newBuilder();
        //同学1
        DemoProto.Clazz classmate = builder.addClassmate(DemoProto.Classmate.newBuilder().setName("轩轩").setSex(true).build()).build();

        byte[] bytes = classmate.toByteArray();
        System.out.println("我试试:"+ classmate.toString());
        classmate.getClassmateList().forEach(item -> {
            String name = item.getName();
            System.out.println("名字："+name);System.out.println("性别："+item.getSex());
        });
    }


    @Test
    public void testProtobufEnum(){
        int number = DemoProto.TargetType.sky.getNumber();
        System.out.println("info:"+number);
    }
}
