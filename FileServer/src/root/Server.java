package root;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import root.handler.TcpHandler;
import root.handler.UdpHandler;

public class Server {
	
	public static final int port = 9453;
	
	public static void main(String[] args) throws IOException {
		DatagramSocket udpSocket = new DatagramSocket(port);
        ServerSocket tcpSocket = new ServerSocket(port);
        
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Thread(new UdpHandler(udpSocket)));
        executorService.execute(new Thread(new TcpHandler(tcpSocket)));
        
        executorService.shutdown();
    }
	
}
