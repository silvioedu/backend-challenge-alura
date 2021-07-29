package com.alura.aluraflix.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.alura.aluraflix.domain.exception.inuse.EntityInUseException;
import com.alura.aluraflix.domain.exception.notfound.EntityNotFoundException;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String MSG_CAMPO_INVALIDO = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {

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

	@ExceptionHandler(EntityInUseException.class)
	public ResponseEntity<Object> handleEntityInUseException(EntityInUseException ex, WebRequest request) {

	    HttpStatus status = HttpStatus.CONFLICT;
	    ProblemTypeEnum problemType = ProblemTypeEnum.IN_USE;
	    String detail = ex.getMessage();
	    var problem = Problem.builder()
            .status(status.value())
            .title(problemType.getTitle())
            .detail(detail)
            .timestamp(OffsetDateTime.now())
            .build();

	    return super.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String detail = MSG_CAMPO_INVALIDO;
		List<Field> problemFields = ex.getBindingResult().getAllErrors().stream().map(objectError -> {
			String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

			String name = objectError.getObjectName();

			if (objectError instanceof FieldError) {
				name = ((FieldError) objectError).getField();
			}

			return Field.builder().name(name).userMessage(message).build();
		}).collect(Collectors.toList());

		var problem = Problem.builder()
				.status(status.value())
				.title(ProblemTypeEnum.INVALID_DATA.getTitle())
				.detail(detail)
				.timestamp(OffsetDateTime.now())
				.fields(problemFields)
				.build();
	
	
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
}
