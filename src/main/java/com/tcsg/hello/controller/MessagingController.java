package com.tcsg.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.tcsg.hello.kafka.Producer;
import com.tcsg.hello.model.Message;
import com.tcsg.hello.model.UserMap;

@Controller
public class MessagingController {
	
	@Autowired
	private Producer producer;	
	
	@MessageMapping("/sendMessage")
	@SendTo("/topic/hello")
	public void sendMessage(@Payload Message message) {
		producer.send(message.serializeToPayload());
	}

	@MessageMapping("/addUser")
	@SendTo("/topic/hello")
	public void addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
		// Add username in web socket session
		headerAccessor.getSessionAttributes().put("userid", message.getSenderid());
		UserMap.addUser(message.getSenderid(), message.serializeToUserData());
		message.setContent(UserMap.getUsers());
		producer.send(message.serializeToPayload());
	}

}