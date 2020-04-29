package com.xuan.akka.actor;

import akka.actor.AbstractActor;
import com.xuan.akka.msg_pojo.MsgPojo;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/29
 * @描述：
 */
public class TestActor extends AbstractActor {
    public TestActor() {
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(MsgPojo.class , info ->{
                System.out.println("testactor接受的内容为["+info.getValue()+"]");

        }).build();
    }
}
