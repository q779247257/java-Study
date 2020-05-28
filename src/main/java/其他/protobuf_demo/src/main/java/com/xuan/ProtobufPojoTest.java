package com.xuan;

import com.google.protobuf.InvalidProtocolBufferException;
import com.xuan.protobuf_pojo.PersonProto;
import org.junit.Test;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/3
 * @描述： 生成命令 protoc --java_out=../java/ xuanxuan.proto
 */
public class ProtobufPojoTest {

    /**
     * protobuf 编译为java类   序列化为 二进制和 反序列化
     */
    @Test
    public void test001() {
        PersonProto.Person.Builder builder = PersonProto.Person.newBuilder();
        builder.setAddress("一个18岁的少年")
                .setAge(18)
                .setSex(true)
                .setBirthday(123L)
                .putOther("key键位","value值")
                .addCars(PersonProto.Car.newBuilder().setColoe("黄色").setName("猫咪").build());
        PersonProto.Person person = builder.build();
        System.out.println("获取protobuf类后："+person.getCarsList());

        //todo 序列化 通过protobuf 生成的java类的内部方法进行序列化
        byte[] bytes = person.toByteArray();
        System.out.println("序列化后的为："+bytes);

        //todo 反序列化 通过protobuf生成的java类的内部方法进行反序列化
        try {
            PersonProto.Person person1 = PersonProto.Person.parseFrom(bytes);
            System.out.println("反序列化后的为："+person1);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}
