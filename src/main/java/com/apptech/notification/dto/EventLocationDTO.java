package com.apptech.notification.dto;

import java.io.Serializable;
import java.util.Date;

public class EventLocationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6121327231797551419L;

	private String clientId;
	private String topic;
	private String clientLongitude;
	private String clientLatitude;
	private Date clientTimestamp;
	private String eventType;
	private String eventDesc;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientLongitude() {
		return clientLongitude;
	}
	public void setClientLongitude(String clientLongitude) {
		this.clientLongitude = clientLongitude;
	}
	public String getClientLatitude() {
		return clientLatitude;
	}
	public void setClientLatitude(String clientLatitude) {
		this.clientLatitude = clientLatitude;
	}
	public Date getClientTimestamp() {
		return clientTimestamp;
	}
	public void setClientTimestamp(Date clientTimestamp) {
		this.clientTimestamp = clientTimestamp;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
}
