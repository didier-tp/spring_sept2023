package tp.appliSpring.core.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import tp.appliSpring.core.dto.ApiError;
import tp.appliSpring.core.exception.NotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		 return new ResponseEntity<>(apiError, apiError.getStatus());
		 }
	
	@Override
   protected ResponseEntity<Object> 
		handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
		 HttpHeaders headers, HttpStatus status, WebRequest request) {
		 String error = "Malformed JSON request";
		 return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error));
		 }
		
	@ExceptionHandler(NotFoundException.class)
		 protected ResponseEntity<Object> handleEntityNotFound(NotFoundException ex) {
		 return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND,ex.getMessage()));
	}

}
