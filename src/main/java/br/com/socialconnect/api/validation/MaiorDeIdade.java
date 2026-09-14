package br.com.socialconnect.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MaiorDeIdadeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface MaiorDeIdade {

    String message() default "Deve ser maior de 18 anos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}