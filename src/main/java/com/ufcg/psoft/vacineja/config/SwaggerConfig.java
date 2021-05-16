package com.ufcg.psoft.vacineja.config;

import com.ufcg.psoft.vacineja.model.enums.TipoUsuarioEnum;
import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", HttpHeaders.AUTHORIZATION, In.HEADER.name());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = getAdminScope();
        authorizationScopes[1] = getCidadaoScope();
        authorizationScopes[2] = getFuncionarioScope();

        return Arrays.asList(
                new SecurityReference("JWT", authorizationScopes));
    }

    private AuthorizationScope getAdminScope() {
        return new AuthorizationScope(TipoUsuarioEnum.ADMINISTRADOR.getValue(), "Acessa as rotas liberadas para Admin");
    }

    private AuthorizationScope getCidadaoScope() {
        return new AuthorizationScope(TipoUsuarioEnum.CIDADAO.getValue(), "Acessa as rotas liberadas para Cidadao");
    }

    private AuthorizationScope getFuncionarioScope() {
        return new AuthorizationScope(TipoUsuarioEnum.FUNCIONARIO.getValue(), "Acessa as rotas liberadas para Funcionário");
    }

}