package com.velocity.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.velocity.dto.ErrorModel;

public class ErrorUtil {
	private ErrorUtil() {

	}

	public static List<ErrorModel> getErrorList(final BindingResult result) {
		final List<ObjectError> errorList = result.getAllErrors();
		final List<ErrorModel> errors = new ArrayList<>();
		for (final ObjectError err : errorList) {
			if (err instanceof FieldError) {
				final FieldError fieldError = (FieldError) err;
				errors.add(new ErrorModel(fieldError.getField(), fieldError.getDefaultMessage()));
			} else {
				errors.add(new ErrorModel(err.getObjectName(), err.getDefaultMessage()));
			}
		}
		return errors;
	}

}
