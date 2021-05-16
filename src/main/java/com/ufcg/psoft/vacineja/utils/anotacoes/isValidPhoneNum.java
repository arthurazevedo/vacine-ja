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

@Pattern(regexp = "^((\\(\\d{2}\\))|\\d{2})?\\d{4,5}[-]?\\d{4}$", message = "Por favor informe um número de telefone válido")
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface isValidPhoneNum {
    String message() default "Por favor informe um número de telefone válido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
