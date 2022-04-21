package root;

import java.io.IOException;
import root.service.ConnectionService;

public class Server {
	
	public static void main(String[] args) throws IOException {
		ConnectionService.init();
    }
	
}
