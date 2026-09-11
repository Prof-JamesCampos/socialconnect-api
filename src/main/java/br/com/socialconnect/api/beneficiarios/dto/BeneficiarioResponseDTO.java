package br.com.socialconnect.api.beneficiarios.dto;

import java.time.LocalDate;

// DTO de SAÍDA: inclui campos gerados pelo servidor
public record BeneficiarioResponseDTO(
        Long idBeneficiario,
        String nome,
        String cpf,
        String telefone,
        String endereco,
        String situacaoVulnerabilidade,
        LocalDate dataCadastro
) {}