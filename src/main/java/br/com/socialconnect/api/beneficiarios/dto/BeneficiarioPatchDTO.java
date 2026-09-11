package br.com.socialconnect.api.beneficiarios.dto;

// DTO para PATCH: todos os campos opcionais
public record BeneficiarioPatchDTO(
        String nome,
        String telefone,
        String endereco,
        String situacaoVulnerabilidade
) {}