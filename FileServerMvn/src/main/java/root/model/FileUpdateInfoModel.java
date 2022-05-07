package root.model;

import java.io.Serializable;

public class FileUpdateInfoModel implements Serializable{

	private static final long serialVersionUID = 1001919453L;
	public String fileName;
	public Integer pageIndex;
	public String newContent;
	public String original;
	public String clientId;

	public String getNewContent() {
		return newContent;
	}
	public void setNewContent(String content) {
		this.newContent = content;
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
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	@Override
	public String toString() {
		return "FileUpdateInfoModel [fileName=" + fileName + ", pageIndex=" + pageIndex + ", newContent=" + newContent
				+ ", original=" + original + ", clientId=" + clientId + "]";
	}


}
