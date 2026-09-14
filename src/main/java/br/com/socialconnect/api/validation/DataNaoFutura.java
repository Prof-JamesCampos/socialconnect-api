package br.com.socialconnect.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DataNaoFuturaValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface DataNaoFutura {

    String message() default "A data não pode ser no futuro";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}