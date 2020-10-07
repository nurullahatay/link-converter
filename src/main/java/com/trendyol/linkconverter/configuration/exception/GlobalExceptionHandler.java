package com.trendyol.linkconverter.configuration.exception;

import com.trendyol.linkconverter.configuration.exception.model.ErrorDTO;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@AllArgsConstructor
class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HttpStatus responseStatus = HttpStatus.BAD_REQUEST;
        String message = ex.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> messageSource.getMessage(objectError.getDefaultMessage(), objectError.getArguments(), LocaleContextHolder.getLocale()))
                .collect(Collectors.joining(", "));
        return new ResponseEntity<>(new ErrorDTO("Bad Request", responseStatus.value(), message), responseStatus);
    }

    @ExceptionHandler(ResolveQueryParamException.class)
    public ResponseEntity<ErrorDTO> handleResolveQueryParamException() {
        HttpStatus responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = messageSource.getMessage("resolve.query.param.exception.message", null, LocaleContextHolder.getLocale());
        return new ResponseEntity<>(new ErrorDTO("Unexpected Error", responseStatus.value(), message), responseStatus);
    }

    @ExceptionHandler(ResolveUrlException.class)
    public ResponseEntity<ErrorDTO> handleResolveUrlException() {
        HttpStatus responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = messageSource.getMessage("resolve.url.exception.message", null, LocaleContextHolder.getLocale());
        return new ResponseEntity<>(new ErrorDTO("Unexpected Error", responseStatus.value(), message), responseStatus);
    }
}