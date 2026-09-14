package br.com.socialconnect.api.beneficiarios.dto;

import br.com.socialconnect.api.validation.CPF;
import jakarta.validation.constraints.*;

// DTO de ENTRADA: apenas campos que o cliente pode enviar
public record BeneficiarioRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
        String nome,

        @NotBlank(message = "CPF é obrigatório")
//        @Pattern(regexp = "\\d{11}|\\d{14}", message = "CPF inválido")
        @CPF(message = "{cpf.invalido}")
        String cpf,

        //@Size(max = 20, message = "Telefone deve ter no máximo 20 caracteres")
        @telefone // Validação customizada!
        String telefone,

        @Size(max = 255, message = "Endereço deve ter no máximo 255 caracteres")
        String endereco,

        @Size(max = 500, message = "Situação deve ter no máximo 500 caracteres")
        String situacaoVulnerabilidade

) {}