package com.apptech.notification.controller;

import java.net.URISyntaxException;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apptech.notification.dto.EventLocationDTO;
import com.apptech.notification.dto.NotificationDTO;
import com.apptech.notification.service.AnalyticsService;
import com.apptech.notification.service.NotificationService;

@RestController
public class NotificationController {

	@Autowired
	private NotificationService service;
	
	@Autowired
	private AnalyticsService service2;

	
	@RequestMapping(value = "/notify", method = RequestMethod.POST)
	public ResponseEntity<NotificationDTO> sendNotification(@RequestBody NotificationDTO dto){
		System.out.println("** sending notification...");
		try {
		Boolean result = service.sendNotification(dto);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/location", method = RequestMethod.POST)
	public ResponseEntity<EventLocationDTO> sendLocation(@RequestBody EventLocationDTO dto){
		System.out.println("** sending location ...");
		try {
			Boolean result = service.sendLocation(dto);
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/notice", method = RequestMethod.GET)
	public ResponseEntity<NotificationDTO> sendNotice(){
		System.out.println("** sending notification 2...");
		NotificationDTO dto = new NotificationDTO();
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
}
