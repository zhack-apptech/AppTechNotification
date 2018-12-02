package com.apptech.notification.service.impl;

import static com.apptech.notification.config.ActiveMQConfig.GENERAL_TOPIC;

import java.net.URI;
import java.net.URISyntaxException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import com.apptech.notification.dto.NotificationDTO;
import com.apptech.notification.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;	// replace when live or remote
	
	@Override
	public boolean sendNotification(NotificationDTO dto) throws URISyntaxException, JMSException, Exception {
		// code save to database here

		// broker service setup
//		BrokerService broker = BrokerFactory.createBroker(new URI("broker:(tcp://localhost:61616)"));
//		broker.start();
		Connection connection = null;
		try {
		// Producer
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url); // throws exception required
		connection = connectionFactory.createConnection();
		
		//Create a non-transactional session to send JMS message to topic
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic(dto.getTopic());
		
		// start connection
		connection.start();
		
		String payload = dto.getMessage();
		Message msg = session.createTextMessage(payload);
		MessageProducer producer = session.createProducer(topic);
		producer.send(msg);
		
		// give time for message to be consumed
			Thread.sleep(3000);
			session.close();
			return true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			if(connection != null) {
				connection.close();
			}
//			broker.stop();
		}
		
//		Destination destination = c
		// send
//		jmsTemplate.convertAndSend(GENERAL_TOPIC, dto.getMessage());
//		return false;
	}

}
