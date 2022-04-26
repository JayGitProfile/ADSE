package root.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	public void run() { //to test concurrency add while loop with thread sleep

		System.out.print("Msg: ");
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			MessageBody obj = mapper.readValue(msg, MessageBody.class);
			System.out.println(obj.getClientId()+" says: "+obj.getCommand());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
