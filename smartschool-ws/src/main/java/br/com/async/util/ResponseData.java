package br.com.async.util;

public class ResponseData {

	public final static String SUCCESS = "sucess";
	public final static String ERROR = "error";
	
	private String message;
	private String status;
	
	public ResponseData(String message, String status) {
		this.message = message;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
