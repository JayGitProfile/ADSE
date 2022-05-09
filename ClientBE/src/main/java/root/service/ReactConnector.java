package root.service;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReactConnector extends WebSocketServer {

	public static Set<WebSocket> conns;
	
	public ReactConnector(int port) throws UnknownHostException {
		super(new InetSocketAddress(port));
		 conns = new HashSet<>();
	}

	public static void broadcastMessage(String msg) {
		System.out.println("try broadcast");
        ObjectMapper mapper = new ObjectMapper();
        try {
        	Map<String, String> map = new HashMap<>();
        	map.put("msg", msg);
            String messageJson = mapper.writeValueAsString(map);
            for (WebSocket sock : conns) {
            	System.out.println("hook "+messageJson);
                sock.send(messageJson);
            }
        } catch (JsonProcessingException e) {
        }
    }
	
	@Override
	public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        conns.add(webSocket);
        System.out.println("on open");		
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		// TODO Auto-generated method stub
		
	}

}
