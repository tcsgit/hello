package com.tcsg.hello.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
	private static final Logger Logger = LoggerFactory.getLogger(Producer.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${kafka.topic}")
	private String topic;

	public void send(String data) {
		Logger.info("sending data='{}' to topic='{}'", data, topic);
		kafkaTemplate.send(topic, data);
	}
}
