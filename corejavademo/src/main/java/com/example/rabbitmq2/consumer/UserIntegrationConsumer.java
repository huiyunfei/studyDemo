package com.example.rabbitmq2.consumer;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserIntegrationConsumer {

	private static Logger LOGGER = LoggerFactory.getLogger(UserIntegrationConsumer.class);


	//同步积分系统
	@RabbitListener(queues="updateIntegration")
	public void synchroIntegration(Message message, Channel channel) throws Exception{

		String msg = new String(message.getBody());
		JSONObject params = JSONObject.parseObject(msg);

		LOGGER.info("mq listener user integration params:{}", params);
		//这里可以处理业务逻辑，根据类型可以设置mq应答机制
		//ResultObj result = userIntegrationFeign.synchroIntegration(params);
		//LOGGER.info("mq user call integral result:{}", result);
		try{
			if("100".equals("")) {
				//成功
//				注：第二个参数值为false代表关闭RabbitMQ的自动应答机制，改为手动应答。
//				在处理完消息时，返回应答状态。
				channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
				
			}else if("404".equals("")){
				//连接失败
//				Thread.currentThread().sleep(5000);
				//丢弃或者退回消息 true退回到queue中/false丢弃
				channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
				//补发消息 true退回到queue中/false只补发给当前的consumer
				//channel.basicRecover(false)
			}else {
				channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
				// 报错错误日志表
				saveQueueLog("userIntegration", params.toString(), "userIntegrationMessage","errormsg");
			}
		}catch( Exception e) {
			e.printStackTrace();
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			// 报错错误日志表
			saveQueueLog("userIntegration", params.toString(), "userIntegrationMessage",e.getMessage());
		}
	}


	/**
	 * 保存队列日志
	 * 
	 * @param queueName
	 * @param queueMsg
	 * @param methodName
	 */
	private void saveQueueLog(String queueName, String queueMsg, String methodName,String error) {
//		QueueLog queueLog = new QueueLog();
//		queueLog.setQueueName(queueName);
//		queueLog.setQueueMsg(queueMsg);
//		queueLog.setMethodName(methodName);
//		queueLog.setErrorLog(error);
//		iQueueLog.saveQueueLog(FastJsonUtil.toJSONObject(JSON.toJSONString(queueLog)));
	}
}
