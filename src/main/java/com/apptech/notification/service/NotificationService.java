package com.apptech.notification.service;

import java.net.URISyntaxException;

import javax.jms.JMSException;

import com.apptech.notification.dto.EventLocationDTO;
import com.apptech.notification.dto.NotificationDTO;

public interface NotificationService {

	boolean sendNotification(NotificationDTO dto) throws URISyntaxException, JMSException, Exception;
	boolean sendLocation(EventLocationDTO dto) throws URISyntaxException, JMSException, Exception;
}
