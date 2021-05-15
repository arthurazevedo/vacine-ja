package com.ufcg.psoft.vacineja.utils.error.exception;

import com.ufcg.psoft.vacineja.utils.error.model.FormErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class FormError {
    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<FormErrorDTO> handler(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<FormErrorDTO> formErrorDtos = new ArrayList<>();

        fieldErrors.forEach(err -> {
            String menssagem = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            FormErrorDTO formError = new FormErrorDTO(err.getField(), menssagem);
            formErrorDtos.add(formError);
        });

        return formErrorDtos;
    }
}