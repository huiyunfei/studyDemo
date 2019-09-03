package com.example.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * Created by hui.yunfei@qq.com on 2019/5/22
 */
//@Component
public class HellowReceive {
    @RabbitListener(queues="topic.message")    //监听器监听指定的Queue
    public void process1(String str) {
        System.out.println("message:"+str);
    }
    @RabbitListener(queues="topic.messages")    //监听器监听指定的Queue
    public void process2(String str) {
        System.out.println("messages:"+str);
    }
}
