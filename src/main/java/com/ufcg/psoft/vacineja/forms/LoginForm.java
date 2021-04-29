package com.ufcg.psoft.vacineja.forms;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
public class LoginForm {
    private String email;
    private String senha;

    public UsernamePasswordAuthenticationToken converter() {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(this.email, this.senha);

        return usernamePasswordAuthenticationToken;
    }
}