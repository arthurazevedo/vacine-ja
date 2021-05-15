package com.ufcg.psoft.vacineja.utils.error.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<?> handleValidacaoException(ValidacaoException exception) {
        return new ResponseEntity<>(exception.getErro(), exception.getStatus());
    }
}
