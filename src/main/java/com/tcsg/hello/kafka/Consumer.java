package com.tcsg.hello.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.tcsg.hello.model.Message;
import com.tcsg.hello.model.UserMap;

@Service
public class Consumer {
	private static final Logger Logger = LoggerFactory.getLogger(Consumer.class);

	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@KafkaListener(topics = "${kafka.topic}", groupId = "#{consumerGroup.GROUPID}")
	public void consume(String data) {
		Logger.info("received data='{}'", data);
        
		Message message = Message.deserializeFromPayload(data);
		
		if (message.getType().equals("JOIN") || message.getType().equals("LEAVE"))
			UserMap.updateUsers(message.getContent());
		
		messagingTemplate.convertAndSend("/topic/hello", message);
	}
}
