package root.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileService {

	final String serverDirPath = System.getProperty("user.home")+"/Desktop/ADSE/Server";
	
	public void getFileList() {
		List<String> fileList = new ArrayList<String>();
		try {
			File[] folder = new File(serverDirPath).listFiles();
			for(File file: folder) {
				System.out.println(file.getName());
			}
		}
		catch(Exception e) {
			
		}
	}
}
