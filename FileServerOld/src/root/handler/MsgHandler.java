package root.handler;

import root.model.MessageBody;

public class MsgHandler implements Runnable {

	public String msg;
	
	public MsgHandler(Object data, boolean isUdp) {
		this.msg = isUdp? toString((byte[]) data) : (String) data;
	}
	
	public String toString(byte[] incomingMsg)
    {
        if(incomingMsg==null)
            return null;
        
        StringBuilder message = new StringBuilder();
        int i = 0;        
        while(incomingMsg[i]!=0) {
        	message.append((char) incomingMsg[i]);
            i++;
        }
        
        return message.toString();
    }
	
	@Override
	public void run() {
		System.out.println("Msg: " + msg);
		//ObjectMapper mapper = new ObjectMapper();
		//MessageBody obj = new MessageBody(msg);
	}

}
