package com.xuan.akka.actor;

import akka.actor.AbstractActor;
import com.xuan.akka.msg_pojo.MsgPojo;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/14
 * @描述：
 */
public class Demo01Actor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(MsgPojo.class, info -> {
            System.out.println("Demo01 accept msg is："+info.getValue());
        }).build();
    }
}
