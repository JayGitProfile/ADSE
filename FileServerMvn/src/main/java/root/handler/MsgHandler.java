package root.handler;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import root.model.FileUpdateInfoModel;
import root.model.TcpMsgBody;
import root.service.FileService;

public class MsgHandler implements Runnable {

	public String msg;
	public byte[] msgBuffer;
	public boolean isUdp;
	
	public MsgHandler(Object data, boolean isUdp) {
		this.isUdp = isUdp;
		if(isUdp) 
			this.msgBuffer = (byte[]) data;
		else
			this.msg = (String) data;
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
		ObjectMapper mapper = new ObjectMapper();
		try {
			if(!isUdp) { //tcp
				TcpMsgBody obj = mapper.readValue(msg, TcpMsgBody.class);
				System.out.println(obj.getClientId()+" says: "+obj.getCommand());
			}
			else {
				ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(msgBuffer));
				Map<String, Object> map = (Map) SerializationUtils.deserialize(msgBuffer);
				System.out.println(map.keySet());
				//FileUpdateInfoModel obj = (FileUpdateInfoModel) SerializationUtils.deserialize(msgBuffer);
				//System.out.println(obj);
				FileService fileService = new FileService();
				//fileService.updateFile(obj);
				iStream.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
