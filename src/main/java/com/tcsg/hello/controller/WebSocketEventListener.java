package com.tcsg.hello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.tcsg.hello.kafka.Producer;
import com.tcsg.hello.model.Message;
import com.tcsg.hello.model.UserMap;

@Component
public class WebSocketEventListener {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

	@Autowired
    private Producer producer;
	
	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		logger.info("Received a new web socket connection");
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

		String userid = (String) headerAccessor.getSessionAttributes().get("userid");
		if(userid != null) {
			logger.info("User Disconnected : " + userid);
			
			UserMap.removeUser(userid);
			
			Message message = new Message();
			message.setType("LEAVE");
			message.setContent(UserMap.getUsers());
			message.setSenderid(userid);

			producer.send(message.serializeToPayload());
        }
    }
}
