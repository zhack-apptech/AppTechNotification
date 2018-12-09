package com.apptech.notification.service;

import javax.jms.Message;
import javax.jms.Session;

import org.springframework.messaging.MessageHeaders;

public interface AnalyticsService {

	boolean receiveLocation(String data, MessageHeaders headers, Message message, Session session);
}
