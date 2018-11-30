package com.apptech.notification.dto;

import java.io.Serializable;

public class NotificationDTO implements Serializable {
	
	public NotificationDTO() {
		
	}

	public NotificationDTO(String message, String topic) {
		super();
		this.message = message;
		this.topic = topic;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 4117866571583869357L;
	private String message;
	private String topic;
	
	
	

}
