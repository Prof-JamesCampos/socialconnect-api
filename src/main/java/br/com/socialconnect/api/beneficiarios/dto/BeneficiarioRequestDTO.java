package br.com.socialconnect.api.beneficiarios.dto;

import jakarta.validation.constraints.*;

// DTO de ENTRADA: apenas campos que o cliente pode enviar
public record BeneficiarioRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
        String nome,

        @NotBlank(message = "CPF é obrigatório")
        @Pattern(regexp = "\\d{11}|\\d{14}", message = "CPF inválido")
        String cpf,

        String telefone,
        String endereco,
        String situacaoVulnerabilidade

) {}