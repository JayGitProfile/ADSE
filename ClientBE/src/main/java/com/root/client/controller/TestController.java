package com.root.client.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.root.client.ClientBeApplication;
import com.root.client.model.MessageBody;
import com.root.client.service.ConnectionService;

@RestController
public class TestController {
	
	@Autowired
	ConnectionService conn;
	
	@GetMapping("/client/{command}")
	public void testMe2(@PathVariable("command") int command) 
			throws IOException {
		MessageBody msg = new MessageBody(99, command);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(msg);
	      
		conn.sendData(jsonString);
	}
}
