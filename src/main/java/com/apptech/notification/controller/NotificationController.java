package com.apptech.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apptech.notification.dto.NotificationDTO;
import com.apptech.notification.service.NotificationService;

@RestController
public class NotificationController {

	@Autowired
	private NotificationService service;
	
	@RequestMapping(value = "/notify", method = RequestMethod.POST)
	public ResponseEntity<NotificationDTO> sendNotification(@RequestBody NotificationDTO dto){
		System.out.println("** sending notification...");
		Boolean result = service.sendNotification(dto);
		dto.setMessage("This is a message");
		dto.setTopic("This is the topic");
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/notice", method = RequestMethod.GET)
	public ResponseEntity<NotificationDTO> sendNotice(){
		System.out.println("** sending notification 2...");
		NotificationDTO dto = new NotificationDTO();
		dto.setMessage("This is a message");
		dto.setTopic("This is the topic");
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
