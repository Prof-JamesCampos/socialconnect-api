package br.com.socialconnect.api.doacoes.dto;

import br.com.socialconnect.api.doacoes.model.TipoDoacao;
import br.com.socialconnect.api.validation.DataNaoFutura;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DoacaoRequestDTO(

        @NotNull(message = "ID do doador é obrigatório")
        Long idDoador,

        @NotNull(message = "Data da doação é obrigatória")
        @DataNaoFutura  // Sua validação customizada!
        LocalDate dataDoacao,

        @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
        BigDecimal valor,

        @NotNull(message = "Tipo de doação é obrigatório")
        TipoDoacao tipo,

        @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
        String descricao

) {}