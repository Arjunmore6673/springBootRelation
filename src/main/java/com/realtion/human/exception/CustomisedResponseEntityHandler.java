package com.realtion.human.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice /// it will add it to all the controllers.
@RestController  ///  convert it to JSON.
public class CustomisedResponseEntityHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest req){
			ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),req.getDescription(false),HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Object>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExcption(UserNotFoundException ex, WebRequest req){
		System.out.println(req);
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),req.getDescription(true),HttpStatus.NOT_FOUND);
	return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);

	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"validation failed",
				ex.getBindingResult().getFieldError().getDefaultMessage().toString(),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}


	/**
	 * Handles the custom exception
	 *
	 * @param ex-storees     the thrown exception
	 * @param request-stores the request
	 * @return the HttpStatus ,message and date of the exception thrown
	 */
	@ExceptionHandler(RelativeException.class)
	public final ResponseEntity<Object> customException(RelativeException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false),ex.getResponseStatus());
		return new ResponseEntity(exceptionResponse, ex.getResponseStatus());
	}


}
