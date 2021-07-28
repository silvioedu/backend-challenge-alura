package com.alura.aluraflix.api.exceptionhandler;

import java.time.OffsetDateTime;

import com.alura.aluraflix.domain.exception.VideoNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(VideoNotFoundException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(VideoNotFoundException ex, WebRequest request) {

	    HttpStatus status = HttpStatus.NOT_FOUND;
	    ProblemTypeEnum problemType = ProblemTypeEnum.NOT_FOUND;
	    String detail = ex.getMessage();
	    var problem = Problem.builder()
            .status(status.value())
            .title(problemType.getTitle())
            .detail(detail)
            .timestamp(OffsetDateTime.now())
            .build();

	    return super.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

}
