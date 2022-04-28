package com.root.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.root.client.service.ConnectionService;

@SpringBootApplication
public class ClientBeApplication {
	
	public static final String clientId = "Client_1";
	public static volatile int clientCount = 0;
	
	public static final int newClConn = 1; //new client connection
	public static final int reqDirCopy = 2; //request a copy of the server directory
	
	public static void main(String[] args) {
		SpringApplication.run(ClientBeApplication.class, args);
		ConnectionService.init();
		
	}

}
