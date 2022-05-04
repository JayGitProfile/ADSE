package com.root.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.root.client.ClientBeApplication;
import com.root.client.service.ConnectionService;
import com.root.client.service.FileService;

@RestController
public class HomeController {
	
	@Autowired
	FileService fileService;
	
	@Autowired
	ConnectionService conn;
	/*
	@GetMapping("/clientcount")
	public int getClientList() {
		
		return ClientBeApplication.clientCount;
	}
	
	@PostMapping("/new")
	public int addNewClient() {
		int clientId = ++ClientBeApplication.clientCount;
		fileService.newClientInit(clientId, conn);
		
		return clientId;
	}
	*/
}
