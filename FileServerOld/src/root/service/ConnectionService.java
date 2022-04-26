package root.service;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import root.handler.TcpHandler;
import root.handler.UdpHandler;

public class ConnectionService {

	public static final int port = 9453;
	private static DatagramSocket udpSocket;
	private static ServerSocket tcpSocket;
	public static ExecutorService mainExecutor;
	
	public static void init() {
		Thread tcp, udp;
		try {
			tcpSocket = new ServerSocket(port);
			udpSocket = new DatagramSocket(port);
			
			mainExecutor = Executors.newFixedThreadPool(2);
	        tcp = new Thread(new UdpHandler(udpSocket));
	        mainExecutor.execute(tcp);
	        udp = new Thread(new TcpHandler(tcpSocket));
	        mainExecutor.execute(udp);
	        
	        mainExecutor.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
