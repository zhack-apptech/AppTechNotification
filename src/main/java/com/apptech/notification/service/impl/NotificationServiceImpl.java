package com.apptech.notification.service.impl;

import static com.apptech.notification.config.ActiveMQConfig.GENERAL_TOPIC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import com.apptech.notification.dto.NotificationDTO;
import com.apptech.notification.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Override
	public boolean sendNotification(NotificationDTO dto) {
		
		jmsTemplate.convertAndSend(GENERAL_TOPIC, dto.getMessage());
		return false;
	}

}
