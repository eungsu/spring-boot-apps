package com.example.demo.web.advice;

import com.example.demo.exception.AppException;
import com.example.demo.web.response.common.RestResponse;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

	@ExceptionHandler(AppException.class)
	public ResponseEntity<RestResponse<Void>> handleAppException(AppException ex) {
		log.error("Application Error: {}", ex.toString());
    
		return ResponseEntity.internalServerError().body(RestResponse.internalServerError(ex.getMessage()));
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<RestResponse<Void>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, Model model) {
		log.error("Request Parameter is miss: {}", ex.toString());
		
		return ResponseEntity.badRequest().body(RestResponse.badRequest("요청파라미터 값이 올바르지 않음"));
	}
	

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<RestResponse<Void>> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
		log.error("Request Parameter is miss: {}", ex.toString());

		return ResponseEntity.badRequest().body(RestResponse.badRequest("요청파라미터 값이 올바르지 않음"));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<RestResponse<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		log.error("Method Argument Not Valid: {}", ex.toString());

		Map<String, String> errors = new HashMap<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		}

		return ResponseEntity.badRequest().body(RestResponse.custom(400, "요청정보 유효성 검증 실패", errors));
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<RestResponse<Void>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		log.error("Method Not supported: {}", ex.toString());

		return ResponseEntity.status(405).body(RestResponse.methodNotAllowed(ex.getMethod() + " 요청은 지원하지 않음"));
	}

	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<RestResponse<Void>> handleDataAccessException(DataAccessException ex) {
		log.error("Data Access Error: {}", ex.toString());
		return ResponseEntity.internalServerError().body(RestResponse.internalServerError("데이터 엑세스 오류"));
	}
  
//	@ExceptionHandler(RuntimeException.class)
//	public ResponseEntity<RestResponse<Void>> handleRuntimeException(RuntimeException ex) {
//		log.error("Internal Server Error: {}", ex.toString());
//		return ResponseEntity.internalServerError().body(RestResponse.internalServerError("내부 서버 오류"));
//	}
}
