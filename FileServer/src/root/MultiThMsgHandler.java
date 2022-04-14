package root;

import root.model.MessageParser;

public class MultiThMsgHandler implements Runnable {

	public byte[] incomingMsg;
	
	public MultiThMsgHandler(byte[] incomingMsg) {
		this.incomingMsg = incomingMsg;
	}
	
	@Override
	public void run() {
		System.out.println("Client:-" + MessageParser.parse(incomingMsg));
	}

	
}
