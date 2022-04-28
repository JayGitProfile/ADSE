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

	public void run() {

		try {
			Socket socket = tcpSocket.accept();
			while(true) { //listens for incoming commands
				try {
					System.out.println("TCP");
					input = new DataInputStream(socket.getInputStream());
					output = new DataOutputStream(socket.getOutputStream());
					String inputString = ""; 
					inputString = input.readUTF();
					System.out.println("dataL "+inputString);
					Thread tcpT = new Thread(new MsgHandler(inputString, false));
					tcpEecutor.execute(tcpT);
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
		while(true) { //listens for incoming commands
			try {
				System.out.println("TCP");

				input = new DataInputStream(socket.getInputStream());
				output = new DataOutputStream(socket.getOutputStream());
				String inputString = ""; 
				inputString = input.readUTF();
				System.out.println("dataL "+inputString);
				Thread tcpT = new Thread(new MsgHandler(inputString, false));
				tcpEecutor.execute(tcpT);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		*/
	}

}
