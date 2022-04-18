package root.handler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UdpHandler implements Runnable{

	DatagramSocket udpSocket;
	
	public UdpHandler(DatagramSocket udpSocket) {
		this.udpSocket = udpSocket;
	}
	
	public UdpHandler(){}
	
	@Override
	public void run() {
		byte[] incomingMsg = new byte[100000];
        DatagramPacket packet = null;
        
        while (true) {
            try {
            	System.out.println("UDP");
            	packet = new DatagramPacket(incomingMsg, incomingMsg.length);
				udpSocket.receive(packet);
				
				ExecutorService executorService = Executors.newCachedThreadPool();  
				executorService.execute(new Thread(new MsgHandler(incomingMsg, true)));
	  
	            incomingMsg = new byte[100000];

			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}

}
