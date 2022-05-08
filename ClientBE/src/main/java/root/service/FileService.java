package root.service;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.stereotype.Service;

import root.ClientBeApplication;
import root.model.FileInfoModel;
import root.model.FileUpdateInfoModel;

@Service
public class FileService {

	final String path = System.getProperty("user.home")+"/Desktop/ADSE/Clients/"+ClientBeApplication.clientId;
	final int linesPerPage = 40;
	
	DataInputStream input;
	DataOutputStream output;
	ObjectOutputStream objOutput;
	ObjectInputStream objInput;
	
	public List<FileInfoModel> getFileNameList() {
		List<FileInfoModel> fileList = new ArrayList<>();
		try {
			File[] folder = new File(path).listFiles();
			for(File file: folder) {
				fileList.add(new FileInfoModel(file));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error: "+e.getLocalizedMessage());
		}
		ConnectionService.sendCommand(98);
		return fileList;
	}
	
	public Map<String, Object> fileRead(String fileName, Integer pageIndex, Integer totalPages) { //big.txt, page 2, total 1000 pages with 20 lines in each page
		Map<String,Object> map = new HashMap<>();
		String content="";
		if(pageIndex==null)
			pageIndex=1;
		
		try {
			System.out.println(fileName+" "+pageIndex+" "+totalPages);
			File file = new File(path+"/"+fileName);
			if(file.exists()) {
				FileReader fReader = new FileReader(file);
				BufferedReader bReader = new BufferedReader(fReader);
				String line;
				int start = (pageIndex*linesPerPage)-(linesPerPage-1);
				float tempTp = 0;
				if(totalPages==null) {
					for(tempTp=1;(line=bReader.readLine())!=null;tempTp++) {
						if(tempTp<=linesPerPage)
							content += line+"\n";
					}
					tempTp /= linesPerPage;
					totalPages = 1;
					for(int i=1;tempTp-i>0;i++,totalPages++);
				}
				else {
					for(int i=1;(line=bReader.readLine())!=null && i<=(pageIndex*linesPerPage);i++) {
						if(i>=start) {
							content += line+"\n";
						}
					}
				}
				map.put(fileName, content);
				map.put("page", pageIndex);
				map.put("totalPages", totalPages);
				
				fReader.close();
			}
		}
		catch(Exception e) {
			System.out.println("Error: "+e.getLocalizedMessage());
		}
		
		return map;
	}
	
	public void updateFile(FileUpdateInfoModel fileUpdateObj) {
		try {
			File fOld = new File(path+"/"+fileUpdateObj.getFileName());
			File fNew = new File(path+"/"+fileUpdateObj.getFileName());
			if(fOld.exists()) {
				if(fileUpdateObj.getNewContent()!=fileUpdateObj.getOriginal()) {
					FileReader fReader = new FileReader(fOld);
					BufferedReader bReader = new BufferedReader(fReader);
					String line;
					int start = (fileUpdateObj.getPageIndex()*linesPerPage)-(linesPerPage-1);
					int end = start+linesPerPage;
					//System.out.println("update starts at line: "+start+" finishes at: "+(end-1));
					List<String> fileDataList = new ArrayList<>();
				
					for(int i=1;(line=bReader.readLine())!=null;i++) {
						if(i>=start && i<end) {
							//System.out.println("dont add line: "+i+" "+line);
						}
						else {
							fileDataList.add(line);
							//System.out.println("add line: "+i+" "+line);
						}
					}
					//System.out.println(start-1+" "+fileDataList.size());
					fileDataList.addAll(start-1, Arrays.asList(fileUpdateObj.getNewContent().split("\n")));
					FileWriter writer = new FileWriter(fNew, false);
					for(String str: fileDataList) {
						//System.out.println("~~ "+str);
						writer.write(str+"\n");
					}
					writer.close();
					fReader.close();
					
					//Map<String, Object> map = new HashMap<>();
					//map.put("update",fileUpdateObj);
					//map.put("clientId", ClientBeApplication.clientId);
					Map<String, Object> map = fileUpdateObj.covertToMap();
					map.put("do", "update");
					
					updateToServer(map);
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error: "+e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	public void updateToServer(Map<String, Object> map) {
		try {
			System.out.println("to server");
			ConnectionService.sendData(SerializationUtils.serialize((Serializable) map));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
	
	/*
	
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
	
	*/
}
