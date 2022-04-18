package root.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpHandler implements Runnable{

	ServerSocket tcpSocket;
	DataInputStream input;
	DataOutputStream output;
	
	public TcpHandler(ServerSocket tcpSocket) {
		this.tcpSocket = tcpSocket;
	}
	
	public TcpHandler() {}
	
	@Override
	public void run() {
		while(true) {
			try {
				System.out.println("TCP");
				Socket socket = tcpSocket.accept();
				input = new DataInputStream(socket.getInputStream());
				output = new DataOutputStream(socket.getOutputStream());
				String inputString = ""; 
				inputString = input.readUTF();
				System.out.println("tcp: ");
				
				//ToDo
				Thread T = new Thread(new MsgHandler(inputString, false));
	            T.start();
	            //System.out.println("MultiThMsgHandler: "+T.getId());
				/*while(true) { 
					input = new DataInputStream(socket.getInputStream());
					inputString = input.readUTF();
					System.out.println("tcp: ");
					
					//ToDo
					Thread T = new Thread(new MsgHandler(inputString, false));
		            T.start();
		            System.out.println("MultiThMsgHandler: "+T.getId());
				}*/
	            System.out.println("end2");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}

}
