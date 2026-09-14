package br.com.socialconnect.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DataNaoFuturaValidator implements ConstraintValidator<DataNaoFutura, LocalDate> {

    @Override
    public boolean isValid(LocalDate data, ConstraintValidatorContext context) {
        // Se for nula, deixa o @NotNull cuidar
        if (data == null) {
            return true;
        }

        // A data deve ser hoje ou no passado
        return !data.isAfter(LocalDate.now());
    }
}