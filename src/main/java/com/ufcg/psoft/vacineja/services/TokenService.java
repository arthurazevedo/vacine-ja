package com.ufcg.psoft.vacineja.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    @Value("${jwt.expiration}")
    private String expiraton;
    @Value("${jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        Date dataCriacao = new Date();
        Date dataExpiracao = new Date(dataCriacao.getTime() + Long.parseLong(expiraton));

        return Jwts.builder()
                .setIssuer("Api Financeiro")
                .setSubject(authentication.getPrincipal().toString())
                .setIssuedAt(dataCriacao)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public String getLogin(String token) {
        Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return body.getSubject();
    }
}