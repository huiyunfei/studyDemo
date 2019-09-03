package com.example.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * Created by hui.yunfei@qq.com on 2019/5/22
 */
//@Component
public class HellowFanoutReceive {
    @RabbitListener(queues="fanout.A")    //监听器监听指定的Queue
    public void process1(String str) {
        System.out.println("fanout.A:"+str);
    }
    @RabbitListener(queues="fanout.B")    //监听器监听指定的Queue
    public void process2(String str) {
        System.out.println("fanout.B:"+str);
    }
}
