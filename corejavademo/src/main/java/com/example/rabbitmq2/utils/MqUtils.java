package com.example.rabbitmq2.utils;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.BasicProperties.Builder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqUtils {
	/**
	 * 通过队列发送消息
	 * 
	 * @param queue
	 *            队列名
	 * @param isDurable
	 *            是否持久化
	 * @param message
	 *            消息内容
	 * @return 是否发送成功
	 */
	public static boolean sendMessageForQueue(String queue, boolean isDurable, String message)
			throws RbtException, IOException, TimeoutException {
		boolean isSuccess = false;
		if (null == message || "".equals(message.trim())) {
			throw new RbtException("发送的消息不能为空");
		}
		Channel channel = null;
		try {
			RabbitConnFactory.initConnFactory();
			channel = RabbitConnFactory.createChannel(RabbitConnFactory.getConnection(false));
			BasicProperties prop = null;
			if (isDurable) {
				prop = MessageProperties.PERSISTENT_TEXT_PLAIN;
			}
			channel.basicPublish("", queue, prop, message.getBytes());
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != channel) {
				channel.close();
				channel = null;
			}
		}
		return isSuccess;
	}

	/**
	 * 通过交换机发送消息
	 * 
	 * @param exchange
	 *            交换机名
	 * @param et
	 *            交换机类型(枚举类型) 默认：fanout
	 * @param routingKey
	 *            如果et不是fanout，那么需要制定routingKey
	 * @param message
	 *            消息内容
	 * @return 是否发送成功
	 */
	public static boolean sendMessageFoExchange(String exchange, ExchangeType et, String routingKey, String message)
			throws RbtException, IOException, TimeoutException {
		boolean isSuccess = false;
		if (null == message || "".equals(message.trim())) {
			throw new RbtException("发送的消息不能为空");
		}
		Channel channel = null;
		try {
			RabbitConnFactory.initConnFactory();
			channel = RabbitConnFactory.createChannel(RabbitConnFactory.getConnection(false));

			if (!"fanout".equals(et.getValue())) {
				if (null == routingKey || "".equals(routingKey.trim())) {
					throw new RbtException("exchange类型不是fanout时，需要传入routingKey");
				}
			}
			channel.basicPublish(exchange, routingKey, null, message.getBytes());
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != channel) {
				channel.close();
				channel = null;
			}
		}
		return isSuccess;
	}

	/**
	 * 通过队列发送延时消息
	 * 
	 * @param queue
	 *            队列名
	 * @param isDurable
	 *            是否持久化
	 * @param expire
	 *            延时时间(ms)
	 * @param message
	 *            消息内容
	 * @return 是否发送成功
	 */
	public static boolean sendDelayMessageForQueue(String queue, boolean isDurable, String expire, String message)
			throws RbtException, IOException, TimeoutException {
		boolean isSuccess = false;
		if (null == message || "".equals(message.trim())) {
			throw new RbtException("发送的消息不能为空");
		}
		Channel channel = null;
		try {
			RabbitConnFactory.initConnFactory();
			channel = RabbitConnFactory.createChannel(RabbitConnFactory.getConnection(false));
			Builder builder = new Builder();
			BasicProperties props = builder.expiration(expire).deliveryMode(2).build();
			channel.basicPublish("", queue, props, message.getBytes());
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != channel) {
				channel.close();
				channel = null;
			}
		}
		return isSuccess;
	}

	/**
	 * 通过交换机发送延时消息
	 * 
	 * @param exchange
	 *            交换机名
	 * @param et
	 *            交换机类型(枚举类型) 默认：fanout
	 * @param routingKey
	 *            如果et不是fanout，那么需要制定routingKey
	 * @param expire
	 *            延时时间(ms)
	 * @param message
	 *            消息内容
	 * @return 是否发送成功
	 */
	public static boolean sendDelayMessageFoExchange(String exchange, ExchangeType et, String routingKey, String expire,
			String message) throws RbtException, IOException, TimeoutException {
		boolean isSuccess = false;
		if (null == message || "".equals(message.trim())) {
			throw new RbtException("发送的消息不能为空");
		}
		Channel channel = null;
		try {
			RabbitConnFactory.initConnFactory();
			channel = RabbitConnFactory.createChannel(RabbitConnFactory.getConnection(false));

			if (!"fanout".equals(et.getValue())) {
				if (null == routingKey || "".equals(routingKey.trim())) {
					throw new RbtException("exchange类型不是fanout时，需要传入routingKey");
				}
			}
			Builder builder = new Builder();
			BasicProperties props = builder.expiration(expire).deliveryMode(2).build();
			channel.basicPublish(exchange, routingKey, props, message.getBytes());
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != channel) {
				channel.close();
				channel = null;
			}
		}
		return isSuccess;
	}
}
