package com.tcsg.hello.kafka;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConsumerGroup {
	
	private static final Logger logger = LoggerFactory.getLogger(ConsumerGroup.class);
	
	public static String GROUPID = "";
	
	static {
		try {
			GROUPID = InetAddress.getLocalHost().toString();
		} catch (UnknownHostException e) {
			logger.error("Local host name could not be resolved.");
		}
	}

}
