package com.example.demo.web.advice;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.demo.exception.AppException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice(annotations = Controller.class)
@Slf4j
public class CustomControllerExceptionHandler {

	@ExceptionHandler(AppException.class)
	public String handleAppException(AppException ex, Model model) {
		log.error("Application Error: {}", ex.toString());

		model.addAttribute("errorCode", 500);
		model.addAttribute("errorMessage", ex.getMessage());
		
		return "error-page";
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, Model model) {
		log.error("Request Parameter is miss: {}", ex.toString());
		
		String parameterName = ex.getParameter().getParameterName(); // 누락된 파라미터 이름 추출
		String parameterType = ex.getParameter().getParameterType().getName(); // 누락된 파라미터 타입 추출
		
		model.addAttribute("errorCode", 400);
		model.addAttribute("errorMessage", String.format("필수 요청 파라미터 '%s' (%s)가 올바르지 않습니다.", parameterName, parameterType));
		
		return "error-page";
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public String handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, Model model) {
		log.error("Request Parameter is miss: {}", ex.toString());
		
		String parameterName = ex.getParameterName(); // 누락된 파라미터 이름 추출
        String parameterType = ex.getParameterType(); // 누락된 파라미터 타입 추출
        
        model.addAttribute("errorCode", 400);
        model.addAttribute("errorMessage", String.format("필수 요청 파라미터 '%s' (%s)가 누락되었습니다.", parameterName, parameterType));
		
		return "error-page";
	}

	@ExceptionHandler(DataAccessException.class)
	public String handleDataAccessException(DataAccessException ex, Model model) {
		log.error("Data Access Error: {}", ex.toString());
		
		model.addAttribute("errorCode", 500);
		model.addAttribute("errorMessage", "데이터베이스 엑세스 작업 중 오류가 발생하였습니다.");
		
		return "error-page";
	}
}
