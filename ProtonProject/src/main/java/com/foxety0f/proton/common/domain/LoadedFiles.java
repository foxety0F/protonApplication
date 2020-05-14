package com.foxety0f.proton.common.domain;

import org.springframework.web.multipart.MultipartFile;

public class LoadedFiles {

	private Integer fileId;
	private String fileName;
	private byte[] byteArray;
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getByteArray() {
		return byteArray;
	}
	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}
}
