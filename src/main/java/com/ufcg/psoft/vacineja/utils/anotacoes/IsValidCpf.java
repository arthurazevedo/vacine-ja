package com.ufcg.psoft.vacineja.utils.anotacoes;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NotBlank(message = "Informe o cpf do cidadão.")
@Size(min = 11, max = 14, message = "Informe um cpf válido.")
@NotNull(message = "Informe o cpf.")
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface IsValidCpf {
    String message() default "Por favor informe um cpf valido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}