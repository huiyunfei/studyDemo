package com.example.rabbitmq.direct;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hui.yunfei@qq.com on 2019/5/20
 * 配置Queue(消息队列).那注意由于采用的是Direct模式,需要在配置Queue的时候,指定一个键,使其和交换机绑定
 */
@Configuration
public class SendConf {
    @Bean
    public Queue queue() {
        return new Queue("hello2");
    }
}




