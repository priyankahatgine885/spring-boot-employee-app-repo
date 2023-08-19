package com.velocity.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.velocity.dto.ErrorModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
	private String message;
	private boolean success;
	private String errorCode;
	private String errorMessage;
	private List<ErrorModel> errorList;

	public BaseResponse(final boolean success) {
		this.success = success;
	}

	public boolean getSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	

	public List<ErrorModel> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<ErrorModel> errorList) {
		this.errorList = errorList;
	}

	public BaseResponse(BaseResponse baseResponse) {
		super();
		this.message = baseResponse.getMessage();
		this.success = baseResponse.getSuccess();
		this.errorCode = baseResponse.getErrorCode();
		this.errorMessage = baseResponse.getErrorMessage();
		this.errorList = baseResponse.getErrorList();
	}

	
}
