package com.velocity.dto;

import java.io.Serializable;

public class ErrorModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private  String field;

	private String message;


	public ErrorModel(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}
	
	
	

}
