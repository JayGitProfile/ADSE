package root.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import root.ClientBeApplication;
import root.model.MsgBody;
import root.service.ConnectionService;

@RestController
public class TestController {
	
	@Autowired
	ConnectionService conn;
	
	@GetMapping("/client/{command}")
	public void testMe2(@PathVariable("command") int command) 
			throws IOException {
		MsgBody msg = new MsgBody(command);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(msg);
	      
		conn.sendData(jsonString);
	}
	
	@GetMapping("/udp/{command}")
	public void testMe(@PathVariable("command") String command) 
			throws IOException {
	      
		conn.sendData(command);
	}
	
}
