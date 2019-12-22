package com.tcsg.hello.kafka;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Component;

@Component
public class ConsumerGroup {
	
	public static String GROUPID = "";
	
	static {
		try {
			GROUPID = InetAddress.getLocalHost().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
