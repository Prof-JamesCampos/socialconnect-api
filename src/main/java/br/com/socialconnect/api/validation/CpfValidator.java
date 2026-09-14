package br.com.socialconnect.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<CPF, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        // 1. Se for nulo ou vazio, deixa o @NotBlank cuidar
        if (cpf == null || cpf.isBlank()) {
            return true;
        }

        // 2. Remove caracteres não numéricos (pontos, traços)
        cpf = cpf.replaceAll("\\D", "");

        // 3. CPF deve ter 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // 4. Rejeita CPFs com todos os dígitos iguais (ex: 111.111.111-11)
        if (cpf.chars().distinct().count() == 1) {
            return false;
        }

        // 5. Valida o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) digito1 = 0;

        if (Character.getNumericValue(cpf.charAt(9)) != digito1) {
            return false;
        }

        // 6. Valida o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) digito2 = 0;

        return Character.getNumericValue(cpf.charAt(10)) == digito2;
    }
}