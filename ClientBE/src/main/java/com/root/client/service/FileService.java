package com.root.client.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.root.client.ClientBeApplication;
import com.root.client.model.FileUpdateInfoModel;

@Service
public class FileService {

	final String path = System.getProperty("user.home")+"/Desktop/ADSE/Clients/"+ClientBeApplication.clientId;
	final int linesPerPage = 40;
	
	final String newPath = System.getProperty("user.home")+"\\Desktop\\ADSE\\Clients\\";

	public List<String> getFileNameList() {
		List<String> fileNameList = new ArrayList<>();
		try {
			File[] folder = new File(path).listFiles();
			
			for(File file: folder)
				fileNameList.add(file.getName());
		}
		catch(Exception e) {
			System.out.println("Error: "+e.getLocalizedMessage());
		}
		
		return fileNameList;
	}
	
	public List<String> fileRead(String fileName, int pageIndex, int totalPages) { //big.txt, page 2, total 1000 pages with 20 lines in each page
		List<String> fileDataList = new ArrayList<>();
		try {
			System.out.println(fileName+" "+pageIndex+" "+totalPages);
			File file = new File(path+"/"+fileName);
			if(file.exists()) {
				System.out.println("found");
				FileReader fReader = new FileReader(file);
				BufferedReader bReader = new BufferedReader(fReader);
				String line;
				int start = (pageIndex*linesPerPage)-(linesPerPage-1);
				System.out.println(start);
				for(int i=1;(line=bReader.readLine())!=null && i<=(pageIndex*linesPerPage);i++) {
					if(i>=start) {
						System.out.println(line);
						fileDataList.add(line);
					}
				}
				fReader.close();
			}
		}
		catch(Exception e) {
			System.out.println("Error: "+e.getLocalizedMessage());
		}
		
		return fileDataList;
	}
	
	/*
	public void updateFile(FileUpdateInfoModel fileUpdateObj) {
		try {
			File file = new File(path+"/"+fileUpdateObj.getFileName());
			if(file.exists()) {
				System.out.println("found");
				FileReader fReader = new FileReader(file);
				BufferedReader bReader = new BufferedReader(fReader);
				String line;
				int start = (pageIndex*linesPerPage)-(linesPerPage-1);
				
				for(int i=1;(line=bReader.readLine())!=null && i<=(pageIndex*linesPerPage);i++) {
					if(i>=start) {
						fileDataList.add(line);
					}
				}
				fReader.close();
			}
		}
		catch(Exception e) {
			System.out.println("Error: "+e.getLocalizedMessage());
		}
	}
	*/
	
	public void newClientInit(int clientId, ConnectionService conn) {
		System.out.println("new client");
		try {
			//String temp = newPath+"Client"+Integer.toString(++ClientBeApplication.clientCount);
			//System.out.println(temp);
			File folder = new File(newPath+"Client"+Integer.toString(clientId));
			if(!folder.exists())
				folder.mkdir();
				conn.sendCommand(clientId, ClientBeApplication.newClConn);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
