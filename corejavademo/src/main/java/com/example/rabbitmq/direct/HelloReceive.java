package com.example.rabbitmq.direct;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * Created by hui.yunfei@qq.com on 2019/5/20
 */
//@Component
public class HelloReceive {
    @RabbitListener(queues="hello")    //监听器监听指定的Queue
    public void processC(User obj) {
        System.out.println("Receive hello:"+obj.toString());
    }
    @RabbitListener(queues="hello2")    //监听器监听指定的Queue
    public void processB(User obj) {
        System.out.println("Receive hello2:"+obj.toString());
    }
}





