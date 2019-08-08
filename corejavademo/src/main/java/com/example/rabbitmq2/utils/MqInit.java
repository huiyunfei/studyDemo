package com.example.rabbitmq2.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqInit {

	public static void main(String[] args) throws IOException, TimeoutException {
		
        ConnectionFactory factory = new ConnectionFactory();
        factory.setPort(5672);  
        factory.setUsername("admin");
        factory.setPassword("Admin123");
        factory.setHost("192.1.1.1");
        factory.setVirtualHost("/test");
        
        Connection connection = factory.newConnection();
       
        Channel channel = connection.createChannel();

        String integrationQueues[] = new String[] {"updateIntegration"};

        for (String queueName : integrationQueues) {

            String exchange = "integration";

            channel.exchangeDeclare(exchange, "topic", true, false, false, null);

            // 声明队列
            channel.queueDeclare(queueName, true, false, false, null);
            // 绑定队列到交换机
            channel.queueBind(queueName, exchange, queueName);
        }
        System.exit(0);

	}
}
