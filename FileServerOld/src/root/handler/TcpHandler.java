package root.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpHandler implements Runnable{

	ServerSocket tcpSocket;
	DataInputStream input;
	DataOutputStream output;
	final ExecutorService tcpEecutor = Executors.newCachedThreadPool();
	
	public TcpHandler(ServerSocket tcpSocket) {
		this.tcpSocket = tcpSocket;
	}
	
	public TcpHandler() {}
	
	@Override
	public void run() {
		while(true) { //listens for incoming commands
			try {
				System.out.println("TCP");
				Socket socket = tcpSocket.accept();
				input = new DataInputStream(socket.getInputStream());
				output = new DataOutputStream(socket.getOutputStream());
				String inputString = ""; 
				inputString = input.readUTF();
				
				Thread tcpT = new Thread(new MsgHandler(inputString, false));
	            tcpEecutor.execute(tcpT);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}

}
