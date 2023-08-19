package com.velocity.exception;

import java.util.List;

import com.velocity.dto.ErrorModel;
import com.velocity.response.BaseResponse;

public enum CustomExceptionEnum {
    VALIDATION_FAILED("100", "Validation failure"),
    INVALID_EMPLOYEE_ID("101", "Invalid employee ID"),
    EMPLOYEE_ID_NOT_NEGATIVE("102", "Employee ID should not negative"),
    EMPLOYEE_NOT_FOUND_EXCEPTION("103", "Employee not found exception"),
    EMPLOYEE_NOT_EXIST("105", "Employee not exist");

    private final String errorCode;
    private final String errorMessage;

    CustomExceptionEnum(final String errorCode, final String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public java.lang.String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public BaseResponse getResponse() {
        final BaseResponse baseResponse = new BaseResponse(false);
        baseResponse.setErrorCode(errorCode);
        baseResponse.setErrorMessage(errorMessage);
        return baseResponse;
    }

    public BaseResponse getResponse(final List<ErrorModel> errorList) {
        final BaseResponse baseResponse = new BaseResponse(false);
        baseResponse.setErrorList(errorList);
        return baseResponse;
    }

}
