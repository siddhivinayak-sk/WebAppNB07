package com.app.controllers.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.dtos.StudentDTO;

public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return StudentDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "required.age", "Age is required!");
	}

}
