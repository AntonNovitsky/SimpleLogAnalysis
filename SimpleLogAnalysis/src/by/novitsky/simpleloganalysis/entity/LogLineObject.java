package by.novitsky.simpleloganalysis.entity;

import java.time.LocalDateTime;

public class LogLineObject {
	
	private String userName;
	private LocalDateTime date;
	private String message;
	
	public LogLineObject() {

	}
	
	public LogLineObject(String userName, LocalDateTime date, String message) {
		this.userName = userName;
		this.date = date;
		this.message = message;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
