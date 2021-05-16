package com.ufcg.psoft.vacineja.utils.anotacoes;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Pattern(regexp = "^(\\d{11})|(\\d{3}[.]\\d{3}[.]\\d{3}[-]\\d{2})$", message = "Por favor informe um cpf valido.")
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface IsValidCpf {
    String message() default "Por favor informe um cpf valido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}