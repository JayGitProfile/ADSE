package root.service;

import java.util.Map;

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
		switch(map.keySet().iterator().next()) {
			case "update":
				FileUpdateInfoModel obj = (FileUpdateInfoModel) map.get("update");
				Server.console(obj.getClientId()+" : Update "+obj.getFileName(),"<");
				
				fileService = new FileService();
				//fileService.updateFile(obj);
		}
	}
}
