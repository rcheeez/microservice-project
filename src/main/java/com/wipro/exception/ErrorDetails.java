package com.wipro.exception;

import java.util.Date;

public class ErrorDetails {

	private Date timestamp;
	private int httpCode;
	private String message;
	private String description;
	
	public ErrorDetails(Date timestamp, int httpCode, String message, String description) {
		super();
		this.timestamp = timestamp;
		this.httpCode = httpCode;
		this.message = message;
		this.description = description;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ErrorDetails [timestamp=" + timestamp + ", httpCode=" + httpCode + ", message=" + message
				+ ", description=" + description + "]";
	}	
}
