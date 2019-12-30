package com.tcsg.hello;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class Application {
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	@RequestMapping("/")
	public String home(Model model) {
		
		String podName = "";
		try {
			podName = InetAddress.getLocalHost().toString();
		} catch (UnknownHostException e) {
			logger.error("Local host name could not be resolved.");
		}
		model.addAttribute("podName", podName);
		return "hello";
    }

    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }

}
