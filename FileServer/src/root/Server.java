package root;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
	
	public static final int port = 9453;
	
	public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(port);
        byte[] incomingMsg = new byte[100000];
        DatagramPacket packet = null;
        
        while (true) {
            packet = new DatagramPacket(incomingMsg, incomingMsg.length);
            socket.receive(packet);
            System.out.println("recieved");
            
            Thread T = new Thread(new MultiThMsgHandler(incomingMsg));
            T.start();
            System.out.println("Thread "+T.getId());
  
            incomingMsg = new byte[100000];
        }
    }
	
}
