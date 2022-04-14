package root;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
	
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(System.in);
		DatagramSocket socket = new DatagramSocket();
		DatagramPacket packet = null;
		InetAddress ip = InetAddress.getLocalHost();
		byte msgBuffer[] = null;

		while (true) {
			String msg = sc.nextLine();
			msgBuffer = msg.getBytes();
			packet = new DatagramPacket(msgBuffer, msgBuffer.length, ip, 9453);
			socket.send(packet);
		}
	}
}
