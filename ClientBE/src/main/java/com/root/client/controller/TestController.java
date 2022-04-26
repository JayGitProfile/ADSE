package com.root.client.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.root.client.model.MessageBody;
import com.root.client.service.ConnectionService;

@RestController
public class TestController {
	
	@Autowired
	ConnectionService conn;
	
	@GetMapping("/client/{msg}")
	public void testMe(@PathVariable("msg") String msg) throws IOException {
		String str = "{ \"msg\": \""+msg+"\"}";	
		
		conn.sendData(str);
	}
	
	@GetMapping("/client/{id}/{command}")
	public void testMe2(@PathVariable("id") String clientId, @PathVariable("command") int command) 
			throws IOException {
		MessageBody msg = new MessageBody(clientId, command);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(msg);
	      
		conn.sendData(jsonString);
	}
}
