package br.com.socialconnect.api.validation;

import jakarta.validation.*;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CpfValidator.class) // ← Aponta para o Validator
@Target({ ElementType.FIELD }) // ← Onde pode ser usada
@Retention(RetentionPolicy.RUNTIME) // ← Disponível em tempo de execução
public @interface CPF {
    String message() default "CPF inválido"; // ← Mensagem padrão
    Class<?>[] groups() default {}; // ← Para validação em grupos (avançado)
    Class<? extends Payload>[] payload() default {}; // ← Metadados adicionais
}