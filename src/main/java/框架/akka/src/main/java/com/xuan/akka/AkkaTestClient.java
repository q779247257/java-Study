package com.xuan.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.xuan.akka.actor.DemoActor;
import com.xuan.akka.msg_pojo.MsgPojo;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/14
 * @描述： Akka demo
 */
public class AkkaTestClient {
    public static void main(String[] args) {
        //创建 ActorSystem 容器
        ActorSystem actorSystem = ActorSystem.create("xuan_akka_system");
        //创建配置类
        Props props = Props.create(DemoActor.class, () -> new DemoActor());

        //如果 不传第二个参数 则Actor 构造到 ActorSytem中
//        Props props = Props.create(DemoActor.class);

        /**
         * 使用 actorOf工厂方法创建 Actor 并接受2个参数
         * param 1 ： One name is Props of Object
         * param 2 ： 一个 String 类型的 Actor name
         */
        ActorRef demoRefr = actorSystem.actorOf(props, "demo_actor");
        ActorRef demoRefr02 = actorSystem.actorOf(props);
        ActorRef demoRefr03 = actorSystem.actorOf(props);


        //创建消息类
        MsgPojo sendPojo = new MsgPojo(1, "这是从AkkaTestClient发送的消息");
        /**
         * 向DemoActor 发送消息
         * 参数1： 发送的消息
         * 参数2： 发件人  ActorRef.noSender()为无发送者
         */
        demoRefr.tell(sendPojo,ActorRef.noSender());
        demoRefr02.tell(sendPojo,ActorRef.noSender());
        demoRefr03.tell(sendPojo,ActorRef.noSender());
//        demoRefr02.forward(sendPojo,actorSystem.deadLetters().);

    }



}
