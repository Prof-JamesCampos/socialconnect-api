package br.com.socialconnect.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TelefoneValidator implements ConstraintValidator<Telefone, String> {

    @Override
    public boolean isValid(String telefone, ConstraintValidatorContext context) {
        // Se for nulo ou vazio, deixa o @NotBlank cuidar
        if (telefone == null || telefone.isBlank()) {
            return true;
        }

        // Remove caracteres não numéricos (parênteses, traços, espaços)
        String numeros = telefone.replaceAll("\\D", "");

        // Telefone brasileiro: 10 dígitos (fixo) ou 11 dígitos (celular com 9)
        // O primeiro dígito após o DDD deve ser 2-9 (não existe telefone começando com 0 ou 1)
        if (numeros.length() != 10 && numeros.length() != 11) {
            return false;
        }

        // Valida se o DDD é válido (11-99, excluindo códigos inexistentes)
        int ddd = Integer.parseInt(numeros.substring(0, 2));
        if (ddd < 11 || ddd > 99) {
            return false;
        }

        // Valida se o número não começa com 0 ou 1 após o DDD
        char primeiroDigitoNumero = numeros.charAt(2);
        if (primeiroDigitoNumero == '0' || primeiroDigitoNumero == '1') {
            return false;
        }

        // Se for celular (11 dígitos), o primeiro dígito deve ser 9
        if (numeros.length() == 11 && primeiroDigitoNumero != '9') {
            return false;
        }

        return true;
    }
}