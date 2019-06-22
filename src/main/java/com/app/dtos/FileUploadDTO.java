package com.app.dtos;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadDTO implements Serializable {
	final private static long serialVersionUID = 1L;
	
	private MultipartFile file;
	private String message;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
