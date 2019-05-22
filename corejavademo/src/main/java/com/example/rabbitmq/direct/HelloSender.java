package com.example.rabbitmq.direct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hui.yunfei@qq.com on 2019/5/20
 */
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String key,User obj){
        amqpTemplate.convertAndSend(key,obj);
    }
}










