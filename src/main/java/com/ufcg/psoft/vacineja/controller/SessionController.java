package com.ufcg.psoft.vacineja.controller;

import com.ufcg.psoft.vacineja.dtos.TokenDTO;
import com.ufcg.psoft.vacineja.dtos.LoginDTO;
import com.ufcg.psoft.vacineja.model.Usuario;
import com.ufcg.psoft.vacineja.repository.UsuarioRepository;
import com.ufcg.psoft.vacineja.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Value("${hash.forca}")
    private int forcaHash;

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody LoginDTO loginDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(forcaHash);

        UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(),
                loginDTO.getSenha());

        try {
            Authentication authentication = authManager.authenticate(dadosLogin);

            Optional<Usuario> usuarioOptional = usuarioServices.findByEmail(loginDTO.getEmail());

            if(usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();

                if(encoder.matches(loginDTO.getSenha(), usuario.getPassword())){
                    String token = tokenService.gerarToken(authentication);
                    return ResponseEntity.ok().body(new TokenDTO(token, "Bearer"));
                }
            }
            return ResponseEntity.badRequest().build();
        }catch(AuthenticationException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping()
    public Usuario teste() {
        return usuarioServices.findAll().get(0);
    }
}