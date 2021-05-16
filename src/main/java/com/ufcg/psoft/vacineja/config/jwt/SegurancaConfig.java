package com.ufcg.psoft.vacineja.config.jwt;

import com.ufcg.psoft.vacineja.model.enums.TipoUsuarioEnum;
import com.ufcg.psoft.vacineja.repository.UsuarioRepository;
import com.ufcg.psoft.vacineja.service.UsuarioService;
import com.ufcg.psoft.vacineja.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SegurancaConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioServices;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new AutenticacaoManager();
    }

    //Configuracao de autenticacao
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //Configuracao de autorizacao as rotas
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                //Lembrar de liberar as portas aqui
                .antMatchers("/h2/**").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.POST,"/cidadao").permitAll()
                .antMatchers("/cidadao/**")
                    .hasAnyAuthority(TipoUsuarioEnum.CIDADAO.getValue(), TipoUsuarioEnum.FUNCIONARIO.getValue())
                .antMatchers(HttpMethod.POST,"/funcionario").hasAuthority(TipoUsuarioEnum.CIDADAO.getValue())
                .antMatchers("/funcionario/**").hasAuthority(TipoUsuarioEnum.ADMINISTRADOR.getValue())
                .antMatchers(HttpMethod.GET,"/vacinas/**")
                    .hasAnyAuthority(TipoUsuarioEnum.ADMINISTRADOR.getValue(), TipoUsuarioEnum.FUNCIONARIO.getValue())
                .antMatchers("/vacinas/**").hasAuthority(TipoUsuarioEnum.ADMINISTRADOR.getValue())
                .antMatchers("/lotes/**").hasAuthority(TipoUsuarioEnum.FUNCIONARIO.getValue())
                .antMatchers("/perfil-vacinacao/**").hasAuthority(TipoUsuarioEnum.FUNCIONARIO.getValue())
                .antMatchers("/registros").hasAuthority(TipoUsuarioEnum.FUNCIONARIO.getValue())
                .antMatchers("/agendamento")
                    .hasAnyAuthority(TipoUsuarioEnum.CIDADAO.getValue(), TipoUsuarioEnum.FUNCIONARIO.getValue())
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioServices), UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().disable();
    }

    //Configuracao recursos estaticos
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html", "/v2/api-docs",
                "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }
}