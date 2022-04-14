package root.model;

public class MessageParser {
	
	private String clientID;
	private String fileName;
	private int line;
	private String newData;
	
	public static StringBuilder parse(byte[] incomingMsg)
    {
        if(incomingMsg==null)
            return null;
        
        StringBuilder message = new StringBuilder();
        //boolean isJSON = ((char) incomingMsg[0])=='{';
        //System.out.println("isJSON: "+isJSON);
        int i = 0;        
        while(incomingMsg[i]!=0) {
        	message.append((char) incomingMsg[i]);
            i++;
        }
        
        return message;
    }
	
}
