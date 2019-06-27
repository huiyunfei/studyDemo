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
    //需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配
    @Test
    public void direct(){
        User user=new User();
        user.setId(1);
        user.setName("yunfei");
        amqpTemplate.convertAndSend("hello2",user);
    }
    //将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词
    @Test
    public void topic(){
        amqpTemplate.convertAndSend("exchange","topic.message","123");
    }
    //不处理路由键。你只需要简单的将队列绑定到交换机上。一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上
    @Test
    public void fanout(){
        amqpTemplate.convertAndSend("fanoutExchange","","123");
    }

}