package com.example.rabbitmq.direct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by hui.yunfei@qq.com on 2019/5/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(value={"DEPLOY=dev"})
public class HelloSenderTest {
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Test
    public void direct(){
        User user=new User();
        user.setId(1);
        user.setName("yunfei");
        amqpTemplate.convertAndSend("hello2",user);
    }
    @Test
    public void topic(){
        amqpTemplate.convertAndSend("exchange","topic.message","123");
    }
    @Test
    public void fanout(){
        amqpTemplate.convertAndSend("fanoutExchange","","123");
    }

}