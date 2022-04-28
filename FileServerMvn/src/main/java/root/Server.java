package root;

import java.io.IOException;

import root.service.ConnectionService;
import root.service.FileService;

public class Server {
	
	public static void main(String[] args) throws IOException {
		ConnectionService.init();
		
		FileService dir = new FileService();
		dir.getFileList();
    }
	
}
