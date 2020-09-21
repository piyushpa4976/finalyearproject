package com.LM.api.model;

public class GlobalResponse {
	private String status;
	private long status_code;
	private Object data;
	private String message;
	private String error_message;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getStatus_code() {
		return status_code;
	}
	public void setStatus_code(long code) {
		this.status_code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	

}
