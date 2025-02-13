package br.com.zupacademy.caico.mercadolivre.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {NaoCadastradoValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NaoCadastrado {

	String message() default "{br.com.zupacademy.caico.casadocodigo}";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
	String fieldName();
	Class<?> domainClass();
}
