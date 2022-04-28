package com.root.client.model;

public class MessageBody {

	private String clientId;
	private int command; //every number denotes a command
	
	public MessageBody(){}
	
	public MessageBody(int clientId, int command) {
		super();
		this.clientId = "Client"+Integer.toString(clientId);
		this.command = command;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public void parse() {
		
	}

	@Override
	public String toString() {
		return "{clientId=" + clientId + ",command=" + command + "}";
	}
	
	
}
