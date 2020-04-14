package com.xuan.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.xuan.akka.msg_pojo.MsgPojo;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/14
 * @描述：
 */
public class DemoActor extends AbstractActor {
    private ActorRef demo02Actor;

    public DemoActor() {
        System.out.println("我的DemoActor 的构造方法被触发了");
        demo02Actor = context().system().actorOf(Props.create(Demo01Actor.class,() -> new Demo01Actor() ) );
        String name = this.getSelf().path().name();
        System.out.println("DemoActor  的名字是:"+name);
    }

    @Override
    public Receive createReceive() {
//        return receiveBuilder().match().build();
        return receiveBuilder().match(MsgPojo.class , info ->{
            System.out.println("接受的内容为["+info.getValue()+"]");
            //更新消息消息
            info.setValue("这是DemoActor设置的消息");
            info.setId(info.getId()+1);
            //send to demo2Actor
            demo02Actor.tell(info,sender());
        }).build();
    }


}
