package com.chubb;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
//it tells spring that this class is for handling exceptions globally

public class GlobalErrorHandler {
	@ExceptionHandler(exception=Exception.class)
	//we used exception=Exception.class to handle all types of exceptions
	
	public Map<String,String> handlerException(MethodArgumentNotValidException exception) {
		Map<String,String> errorMap =  new HashMap<>();
		List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
		errorList.forEach((error)->{
			//we define the field name
			//field name means which property has the problem
			String fieldName=((FieldError)error).getField();
			String message = error.getDefaultMessage();
			errorMap.put(fieldName, message);
		});
		return errorMap;
		
		
	}
}