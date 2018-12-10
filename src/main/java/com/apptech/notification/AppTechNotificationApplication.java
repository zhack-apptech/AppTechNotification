package com.apptech.notification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.web.client.RestTemplate;

import com.apptech.notification.dto.EventLocationDTO;


@SpringBootApplication
public class AppTechNotificationApplication {

	@Autowired
	private static RestTemplate restTemplate;

	@Autowired
	private static Environment env;
	
	// URL of the JMS Server
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
//	private static String url = "tcp://www.apptechgateway.io:61616";	// replace when local
	private static String subject = "analytics";
	
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
			TopicSubscriber subscriber = session.createSubscriber(topic);
			subscriber.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					ActiveMQTextMessage textMessage = (ActiveMQTextMessage) message;
					
					try {
						
//					byte[] byteData = null;
//					byteData = new byte[ (int)bytesMessage.getBodyLength()];
//					bytesMessage.readBytes(byteData);
//					bytesMessage.reset();
//					String resultMessage = new String(byteData);
					String resultMessage = textMessage.getText();
					System.out.println("Message: " + resultMessage);
					/**
					 * PROCESS MESSAGE HERE
					 */
					System.out.println("PROCESSING MESSAGE IN NOTIFICATIONSERVER");
					JSONObject obj = new JSONObject();
					obj.put("clientId", "desktop_id");
					obj.put("clientLongitude", "124.0983");
					obj.put("clientLatitude", "83.0982");
					obj.put("eventType", "location_change");
					obj.put("eventDesc","Description");
					obj.put("clientTimestamp", (new Date()).toString());
					obj.put("topic", "analytics");
					
					// TODO: send obj to microservices
					EventLocationDTO dto = new EventLocationDTO();
					dto.setClientId(obj.getString("clientId"));
					dto.setClientId(obj.getString("clientLongitude"));
					dto.setClientId(obj.getString("clientLatitude"));
					dto.setClientId(obj.getString("eventType"));
					dto.setClientId(obj.getString("eventDesc"));
					dto.setClientId(obj.getString("clientTimestamp"));
					dto.setClientId(obj.getString("topic"));
					processData(dto);
					
					
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				private void processData(EventLocationDTO dto) {
					System.out.println("process data in main[] of notification server");
					String webClientUrl = env.getProperty("microservice.url.event.location");
					System.out.println(" url: " + webClientUrl);
					HttpHeaders headers = new HttpHeaders();
					List<MediaType> mediaTypes = new ArrayList<>();
					mediaTypes.add(MediaType.APPLICATION_JSON);
					headers.setContentType(MediaType.APPLICATION_JSON);
					headers.setAccept(mediaTypes);
					
					
					HttpEntity<EventLocationDTO> entity = new HttpEntity<>(dto, headers);

					restTemplate.postForObject(webClientUrl, entity, EventLocationDTO.class);
					
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
