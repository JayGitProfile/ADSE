package root.service;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import root.Server;
import root.model.FileUpdateInfoModel;
import root.model.TcpCommandModel;

public class MsgProcessor {

	FileService fileService;
	
	public void tcpProcess(String msg) {
		ObjectMapper mapper = new ObjectMapper();
		TcpCommandModel obj;
		try {
			obj = mapper.readValue(msg, TcpCommandModel.class);
			switch(obj.getCommand()) {
				case 0:
					Server.console(obj.getClientId()+" connected", "*");
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public void udpProcess(Map<String, Object> map) {
		System.out.println(map.keySet());
		switch((String)map.get("do")) {
			case "update":
				FileUpdateInfoModel fileObj = new FileUpdateInfoModel(map);
				Server.console(fileObj.getClientId()+" : Update "+fileObj.getFileName(),"<");
				
				fileService = new FileService();
				//fileService.updateFile(obj);
				
				Server.console("Updating to other clients",">");
				ConnectionService.sendData(SerializationUtils.serialize((Serializable) map));
		}
	}
}
