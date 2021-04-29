package com.ufcg.psoft.vacineja.controller;

import com.ufcg.psoft.vacineja.dtos.TokenDto;
import com.ufcg.psoft.vacineja.forms.LoginForm;
import com.ufcg.psoft.vacineja.models.Usuario;
import com.ufcg.psoft.vacineja.repository.UsuarioRepository;
import com.ufcg.psoft.vacineja.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class SessionController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioServices;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody LoginForm loginForm) {
        UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken(
                loginForm.getEmail(),
                loginForm.getSenha());
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);

            Optional<Usuario> usuarioOptional = usuarioServices.findByEmail(loginForm.getEmail());

            if(usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();

                if(loginForm.getSenha().equals(usuario.getPassword())){
                    String token = tokenService.gerarToken(authentication);
                    return ResponseEntity.ok().body(new TokenDto(token, "Bearer"));
                }
            }
            return ResponseEntity.badRequest().build();
        }catch(AuthenticationException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping()
    public String teste() {
        return "Funcionou";
    }
}