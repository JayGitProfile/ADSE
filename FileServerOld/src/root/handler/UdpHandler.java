package root.handler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UdpHandler implements Runnable{

	DatagramSocket udpSocket;
	final ExecutorService udpExecutor = Executors.newCachedThreadPool();
	
	public UdpHandler(DatagramSocket udpSocket) {
		this.udpSocket = udpSocket;
	}
	
	public UdpHandler(){}
	
	@Override
	public void run() {
		byte[] incomingMsg = new byte[100000];
        DatagramPacket packet = null;
        while (true) { //listens for incoming msgs
            try {
            	System.out.println("UDP");
            	packet = new DatagramPacket(incomingMsg, incomingMsg.length);
				udpSocket.receive(packet);
				
				Thread udpT = new Thread(new MsgHandler(incomingMsg, true));				
				udpExecutor.execute(udpT);
				
	            incomingMsg = new byte[100000];
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}

}
