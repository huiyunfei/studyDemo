package com.example.rabbitmq2.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

@Component
public class RabbitConnFactory {
	private static ConnectionFactory factory;
	private static Connection conn;
	
	private static String host;
	
	
	private static int port;
	
	
	private static String username;
	
	
	private static String password;
	
	
	private static String virtualHost;
	

	@Value("${spring.rabbitmq.host}")
	public void setHost(String host) {
		RabbitConnFactory.host = host;
	}

	@Value("${spring.rabbitmq.port}")
	public void setPort(int port) {
		RabbitConnFactory.port = port;
	}

	@Value("${spring.rabbitmq.username}")
	public void setUsername(String username) {
		RabbitConnFactory.username = username;
	}

	@Value("${spring.rabbitmq.password}")
	public void setPassword(String password) {
		RabbitConnFactory.password = password;
	}
	@Value("${spring.rabbitmq.virtual-host}")
	public void setVirtualHost(String virtualHost) {
		RabbitConnFactory.virtualHost = virtualHost;
	}
	
	private RabbitConnFactory(){
		
	}
	
	/**
	 * 初始化连接工厂类
	 * */
	public static void initConnFactory(){
		if(null==factory){
			factory = new ConnectionFactory();
			factory.setHost(host);
			factory.setPort(port);
			factory.setUsername(username);
			factory.setPassword(password);
			factory.setVirtualHost(virtualHost);
		}
	}
	
	/**
	 * 创建一个新的连接(不管是否已经有连接)
	 * @param isCreateNew true每次都创建新的连接，false创建一个可以一直使用的连接，不会每次都去创建新的连接
	 * @return Connection
	 * @throws RbtException 
	 * @throws TimeoutException 
	 * @throws IOException 
	 * */
	private static Connection createNewConnection(boolean isCreateNew) throws RbtException, IOException, TimeoutException{
		if(null==factory){
			throw new RbtException("连接工厂没有初始化");
		}
		if(isCreateNew){
			return factory.newConnection();
		}else{
			if(null == conn){
				conn = factory.newConnection();
			}
			return conn;
		}
	}
	
	/**
	 * 获取一个连接
	 * @param isCreateNew true每次都获取一个新的连接，false创建一个连接，如果已经存在连接，那么就返回已有的连接
	 * @throws TimeoutException 
	 * @throws IOException 
	 * @throws RbtException 
	 * */
	public static Connection getConnection(boolean isCreateNew) throws RbtException, IOException, TimeoutException{
		return createNewConnection(false);
	}
	
	/**
	 * 创建频道链路
	 * @param Connection
	 * @param exchangeName(如果没有则传null)
	 * @param ExchangeType(如果exchangeName为空，那么这个参数即使有值也无效，默认fanout)
	 * @param queueName(如果exchangeName不为空那么这个字段可以忽略，如果)
	 * @throws RbtException 
	 * @throws IOException 
	 * */
	public static Channel createChannel(Connection connection) throws RbtException, IOException{
		if(null == connection){
			throw new RbtException("没有创建连接");
		}
		Channel channel = connection.createChannel();
//		if(null!=exchangeName && !"".equals(exchangeName.trim())){
//			if(null == eType){
//				eType = ExchangeType.FANOUT;
//			}
//			channel.exchangeDeclare(exchangeName, eType.getValue());
//		}else{
//			if(null == queueName || "".equals(queueName.trim())){
//				throw new RbtException("至少需要有一个队列名称");
//			}
//			channel.queueDeclare(arg0, arg1, arg2, arg3, arg4)
//		}
		
		return channel;
	}
	
	/**
	 * 创建队列（如果不存在才创建，否则直接获取）
	 * @param queue 队列名
	 * @param isDurable 是否持久化
	 * @param isAutoDel 是否自动删除消息
	 * @return DeclareOk 队列信息
	 * @throws TimeoutException 
	 * @throws IOException 
	 * @throws RbtException 
	 * */
	public static com.rabbitmq.client.AMQP.Queue.DeclareOk createQueue(String queue, boolean isDurable, boolean isAutoDel) throws RbtException, IOException, TimeoutException{
		initConnFactory();
		Channel channel = createChannel(getConnection(false));
		com.rabbitmq.client.AMQP.Queue.DeclareOk declareOk = null;
		try{
			declareOk = channel.queueDeclare(queue, isDurable, false, isAutoDel, null);
		} finally{
				if(null!=channel){
					channel.close();
					channel = null;
				}
				
		}
		return declareOk;
	}
	
	/**
	 * 创建延时消息队列（如果不存在才创建，否则直接获取）
	 * @param queue 队列名
	 * @param isDurable 是否持久化
	 * @param isAutoDel 是否自动删除消息
	 * @param toExchange 消息转至的exchange名称
	 * @param toRoutingKey 消息转至的routingKey
	 * @return DeclareOk 队列信息
	 * @throws TimeoutException 
	 * @throws IOException 
	 * @throws RbtException 
	 * */
	public static com.rabbitmq.client.AMQP.Queue.DeclareOk createDelayQueue(String queue, boolean isDurable, boolean isAutoDel,String toExchange,String toRoutingKey) throws RbtException, IOException, TimeoutException{
		initConnFactory();
		Channel channel = createChannel(getConnection(false));
		com.rabbitmq.client.AMQP.Queue.DeclareOk declareOk = null;
		try{
			HashMap<String, Object> arguments = new HashMap<String, Object>();  
	        arguments.put("x-dead-letter-exchange", toExchange);  
	        arguments.put("x-dead-letter-routing-key", toRoutingKey); 
			declareOk = channel.queueDeclare(queue, isDurable, false, isAutoDel, arguments);
		} finally{
				if(null!=channel){
					channel.close();
					channel = null;
				}
				
		}
		return declareOk;
	}
	
	/**
	 * 创建交换机（如果不存在才创建，否则直接获取）
	 * @param exchange 交换机名
	 * @param ExchangeType 交换机类型,默认:fanout
	 * @param isAutoDel 是否自动删除消息
	 * @return DeclareOk 队列信息
	 * @throws TimeoutException 
	 * @throws IOException 
	 * @throws RbtException 
	 * */
	public static com.rabbitmq.client.AMQP.Exchange.DeclareOk createExchange(String exchange, ExchangeType et, boolean isDurable) throws RbtException, IOException, TimeoutException{
		initConnFactory();
		Channel channel = createChannel(getConnection(false));
		if (null == et) {
			et = ExchangeType.FANOUT;
		}
		System.out.println("[X]exchange type is "+et.getValue());
		com.rabbitmq.client.AMQP.Exchange.DeclareOk declareOk = null;
		try{
			declareOk = channel.exchangeDeclare(exchange, et.getValue(),isDurable);
		} finally{
				if(null!=channel){
					channel.close();
					channel = null;
				}
				
		}
		return declareOk;
	}
	
	/**
	 * 创建交换机（如果不存在才创建，否则直接获取）
	 * @param exchange 交换机名
	 * @param ExchangeType 交换机类型,默认:fanout
	 * @param isAutoDel 是否自动删除消息
	 * @return DeclareOk 队列信息
	 * @throws TimeoutException 
	 * @throws IOException 
	 * @throws RbtException 
	 * @throws TimeoutException
	 * */
	public static boolean bindQueueForExchange(String exchange, String queue, String routingKey) throws RbtException, IOException, TimeoutException{
		boolean isSuccess = false;
		initConnFactory();
		Channel channel = createChannel(getConnection(false));
		try {
			channel.queueBind(queue, exchange, routingKey);
			isSuccess = true;
		} finally{
			if(null!=channel){
				channel.close();
				channel = null;
			}
			
		}
		return isSuccess;
	}
	
}
