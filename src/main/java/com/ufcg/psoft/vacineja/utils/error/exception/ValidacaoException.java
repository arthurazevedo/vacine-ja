package com.ufcg.psoft.vacineja.utils.error.exception;

import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;
import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ValidacaoException extends RuntimeException {
    private final ErroDeSistema erroDeSistema;

    public ValidacaoException(ErroDeSistema erro) {
        this.erroDeSistema = erro;
    }

    public ErroDeSistema getErro() {
        return this.erroDeSistema;
    }

    public HttpStatus getStatus() { return this.erroDeSistema.getStatus(); }
}
