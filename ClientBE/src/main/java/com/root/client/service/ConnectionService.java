package com.root.client.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.root.client.ClientBeApplication;
import com.root.client.model.MessageBody;

@Service
public class ConnectionService {

	private static final int port = 9453;

	static DataInputStream input;
	static DataOutputStream output;
	static DatagramSocket udpSocket;
	static DatagramPacket packet;
	static InetAddress ip;
	static Socket socket;
	static ObjectMapper mapper = new ObjectMapper();

	public static void init() {
		System.out.println("init");
		try {
			System.out.println(port);
			socket = new Socket("localhost", port);
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());	
			udpSocket = new DatagramSocket();
			packet = null;
			ip = InetAddress.getLocalHost();

			MessageBody msg = new MessageBody(ClientBeApplication.clientCount, 20);
			output.writeUTF(mapper.writeValueAsString(msg));
			/*
			for(int i=0;i<4;i++) {
				output.writeUTF(mapper.writeValueAsString(msg));
			}
			output.writeUTF(mapper.writeValueAsString(msg));
			output = new DataOutputStream(socket.getOutputStream());	
			String st = "mmmm";
			output.writeUTF(st);
*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//for udp
	public void sendData(String msg) { //obj -> string -> byte
		byte msgBuffer[] = null;

		try {
			msgBuffer = msg.getBytes();
			packet = new DatagramPacket(msgBuffer, msgBuffer.length, ip, port);

			udpSocket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	//for tcp
	public void sendCommand(int clientId, int command) {
		try {
			MessageBody msg = new MessageBody(clientId, command);
			System.out.println("new "+msg);
			output.writeUTF("bro please");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
