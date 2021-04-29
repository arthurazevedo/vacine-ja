package com.ufcg.psoft.vacineja.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
public class LoginDTO {
    private String email;
    private String senha;

    public UsernamePasswordAuthenticationToken converter() {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(this.email, this.senha);

        return usernamePasswordAuthenticationToken;
    }
}