package com.apptech.notification;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AppTechNotificationApplication {

	// URL of the JMS Server
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static String subject = "topic.analytics";
	
	public static void main(String[] args) {
		// Get the JMS Connection from the server
		TopicConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		try {
			TopicConnection connection = connectionFactory.createTopicConnection();
			connection.start();
			
			//Create session for sending messages
			TopicSession session = connection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
			// Get the topic
			Topic topic = session.createTopic(subject);
			
			
			// Message Consumer to receive message
			MessageConsumer consumer = session.createSubscriber(topic);
			consumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					BytesMessage bytesMessage = (BytesMessage) message;
					
					try {
						
					byte[] byteData = null;
					byteData = new byte[ (int)bytesMessage.getBodyLength()];
					bytesMessage.readBytes(byteData);
					bytesMessage.reset();
					String resultMessage = new String(byteData);
					System.out.println("Message: " + resultMessage);
					
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			// Message
			
		} catch (Exception e) {
			System.out.println("ERROR in connection: " + e.getMessage());
		}
		
		
		
		
		
		
		
		SpringApplication.run(AppTechNotificationApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}	
	
	@Bean
	public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory,
																DefaultJmsListenerContainerFactoryConfigurer configurer){
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		factory.setPubSubDomain(true);
		return factory;
	}
}
