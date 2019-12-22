package com.tcsg.hello;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class Application {
	
	@RequestMapping("/")
	public String home(Model model) {
		
		String podName = "";
		try {
			podName = InetAddress.getLocalHost().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		model.addAttribute("podName", podName);
		return "hello";
    }

    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }

}
