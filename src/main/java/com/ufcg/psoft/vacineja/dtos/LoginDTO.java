package com.ufcg.psoft.vacineja.dtos;

import com.ufcg.psoft.vacineja.utils.anotacoes.IsValidEmail;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
public class LoginDTO {
	@NotNull(message = "Informe o seu email.")
	@IsValidEmail()
    private String email;
	
	@Size(min = 6, max = 15, message = "Informe uma senha que contenha de 6 a 15 caracteres.")
	@NotNull(message = "Informe sua senha.")
    private String senha;

    public UsernamePasswordAuthenticationToken converter() {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(this.email, this.senha);

        return usernamePasswordAuthenticationToken;
    }
}