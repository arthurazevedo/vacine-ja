package com.ufcg.psoft.vacineja.utils.error.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroDeSistema implements Serializable {
    private String erro;
    private HttpStatus status;

    public ErroDeSistema(String mensagem) {
        this.status = HttpStatus.BAD_REQUEST;
        this.erro = mensagem;
    }

    public ErroDeSistema(String mensagem, HttpStatus status) {
        this.status = status;
        this.erro = mensagem;
    }

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }
}
