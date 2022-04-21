package com.root.client.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.root.client.ClientBeApplication;

public class DirectoryService {

	final String path = System.getProperty("user.home")+"/Desktop/ADSE/Client";

	public void getFileNameList() {
		try {
			File[] folder = new File(path+"/"+ClientBeApplication.clientId).listFiles();
			List<String> fileNameList = new ArrayList<>();
			for(File file: folder)
				fileNameList.add(file.getName());
			
			System.out.println(fileNameList);
		}
		catch(Exception e) {
			System.out.println("Error: "+e.getLocalizedMessage());
		}
	}
}
