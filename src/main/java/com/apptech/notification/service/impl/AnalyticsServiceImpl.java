package com.apptech.notification.service.impl;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.broker.BrokerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.apptech.notification.service.AnalyticsService;



@Service
public class AnalyticsServiceImpl implements AnalyticsService {

	
	
	@JmsListener( destination = "topic.analytics", containerFactory = "topicListenerFactory")
	public boolean receiveLocation(@Payload String data,@Headers MessageHeaders headers, Message message, Session session) {
		
		Connection connection = null;
		
		try {
			
			
			
		} catch( Exception e) {
			
		}
		
		
		System.out.println("Data sent: " + data);
		
		
		return false;
	}

}
