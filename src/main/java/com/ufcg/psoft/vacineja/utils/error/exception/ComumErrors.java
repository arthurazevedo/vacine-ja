package com.ufcg.psoft.vacineja.utils.error.exception;

import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@RestControllerAdvice
public class ComumErrors {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErroDeSistema handler(MethodArgumentTypeMismatchException ex) {
        return new ErroDeSistema(ex.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErroDeSistema handler(MissingServletRequestParameterException ex) {
        return new ErroDeSistema(ex.getMessage());
    }
}