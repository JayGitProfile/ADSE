package root.handler;

import root.model.MessageParser;

public class MsgHandler implements Runnable {

	public String msg;
	
	public MsgHandler(Object data, boolean isUdp) {
		this.msg = isUdp? MessageParser.parse((byte[]) data) : (String) data;
	}
	
	@Override
	public void run() {
		System.out.println("Msg: " + this.msg);
	}

	
}
