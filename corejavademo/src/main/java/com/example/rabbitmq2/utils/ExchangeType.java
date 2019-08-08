package com.example.rabbitmq2.utils;

public enum ExchangeType {
	FANOUT("fanout"),TOPIC("topic"),DIRECT("direct");
	
	private String val;
	private ExchangeType(String value){
		this.val = value;
	}
	
	public String getValue(){
		return val;
	}
}
