package com.root.client.model;

import com.root.client.ClientBeApplication;

public class FileUpdateInfoModel {

	public int lineNum;
	public String content;
	public String fileName;
	public String clientId = ClientBeApplication.clientId;
	
	public int getLineNum() {
		return lineNum;
	}
	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	@Override
	public String toString() {
		return "FileUpdateInfoModel [lineNum=" + lineNum + ", content=" + content + ", fileName=" + fileName
				+ ", clientId=" + clientId + "]";
	}
	
}
