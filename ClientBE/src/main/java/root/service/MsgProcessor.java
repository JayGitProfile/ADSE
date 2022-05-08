package root.service;

import java.util.Map;
import java.util.Set;

import root.ClientBeApplication;
import root.model.FileUpdateInfoModel;

public class MsgProcessor {

	FileService fileService;
	
	public void udpProcess(Map<String, Object> map) {
		switch((String)map.get("do")) {
			case "update":
				FileUpdateInfoModel fileObj = new FileUpdateInfoModel(map);
				System.out.println(fileObj);
		}
	}
}
