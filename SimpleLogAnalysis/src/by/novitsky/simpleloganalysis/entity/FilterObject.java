package by.novitsky.simpleloganalysis.entity;

import java.time.LocalDateTime;

public class FilterObject {
	
	private String userName;
	private LocalDateTime timeStart;
	private LocalDateTime timeEnd;
	private String message;


	public FilterObject() {
		timeStart = LocalDateTime.MIN;
		timeEnd = LocalDateTime.MAX;
	}
	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public LocalDateTime getTimeStart() {
		return timeStart;
	}


	public void setTimeStart(LocalDateTime timeStart) {
		this.timeStart = timeStart;
	}


	public LocalDateTime getTimeEnd() {
		return timeEnd;
	}


	public void setTimeEnd(LocalDateTime timeEnd) {
		this.timeEnd = timeEnd;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

}
