package com.xuan.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.xuan.akka.actor.TestActor;
import com.xuan.akka.msg_pojo.MsgPojo;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/29
 * @描述：
 */
public class ActorTest {
    public static void main(String[] args) throws InterruptedException {
        {
            //创建 ActorSystem 容器
            ActorSystem actorSystem = ActorSystem.create("xuan_akka_system");
            //创建配置类
            Props props = Props.create(TestActor.class, () -> new TestActor());

            ActorRef testActorRef = actorSystem.actorOf(props, "demo_actor");


            //创建消息类
            MsgPojo sendPojo = new MsgPojo(1, "这是从AkkaTestClient发送的消息");

//            testActor.tell(sendPojo,ActorRef.noSender());
            //秒
            TimeUnit timeUnit = TimeUnit.SECONDS;
            //纳秒
            TimeUnit nanoseconds = TimeUnit.NANOSECONDS;
            //毫秒
            TimeUnit milliseconds = TimeUnit.MILLISECONDS;
            //微妙
            TimeUnit microseconds = TimeUnit.MICROSECONDS;
            //分钟
            TimeUnit minutes = TimeUnit.MINUTES;
            //小时
            TimeUnit hours = TimeUnit.HOURS;
            //天
            TimeUnit days = TimeUnit.DAYS;



            /**
             * 发送定时消息
             */
            actorSystem.scheduler()//调度程序
                    .scheduleOnce(//计划（安排一次）
                            //参数1 设置时间
                            Duration.create(5, timeUnit),
                            //参数2 设置Actor
                            testActorRef,
                            //参数3 设置消息
                            sendPojo,
                            //设置调度员
                            actorSystem.dispatcher(),
                            //设置发送员
                            testActorRef
                    );
            Thread.currentThread().sleep(9000);
            System.out.println("主线程执行完毕");


        }
    }
}
