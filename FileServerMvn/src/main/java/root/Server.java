package root;

import java.io.IOException;

import root.service.ConnectionService;
import root.service.DirectoryService;

public class Server {
	
	public static void main(String[] args) throws IOException {
		ConnectionService.init();
		
		DirectoryService dir = new DirectoryService();
		dir.getFileList();
    }
	
}
