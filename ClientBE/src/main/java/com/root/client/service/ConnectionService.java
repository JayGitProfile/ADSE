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

@Service
public class ConnectionService {
	
	private static final int port = 9453;
	
	static DataInputStream input;
	static DataOutputStream output;
	static DatagramSocket udpSocket;
	static DatagramPacket packet;
	static InetAddress ip;
	
	public static void init() {
		System.out.println("init");
		Socket socket;
		try {
			System.out.println(port);
			socket = new Socket("localhost", port);
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());	
			udpSocket = new DatagramSocket();
			packet = null;
			ip = InetAddress.getLocalHost();
			
			output.writeUTF("client 2 connected");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendData(String msg) {
		byte msgBuffer[] = null;
		
		try {
			msgBuffer = msg.getBytes();
			packet = new DatagramPacket(msgBuffer, msgBuffer.length, ip, port);
			
			udpSocket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
