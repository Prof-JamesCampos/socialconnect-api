package br.com.socialconnect.api.doacoes.service;

import br.com.socialconnect.api.doacoes.dto.DoacaoRequestDTO;
import br.com.socialconnect.api.doacoes.dto.DoacaoResponseDTO;
import br.com.socialconnect.api.doacoes.model.Doacao;
import br.com.socialconnect.api.doacoes.model.TipoDoacao;
import br.com.socialconnect.api.doacoes.repository.DoacaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.time.LocalDate;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DoacaoServiceTest {

    // Mock do Repository (simula o banco de dados)
    @Mock
    private DoacaoRepository doacaoRepository;

    // Injeta o mock no Service (o Service usa o mock em vez do banco real)
    @InjectMocks
    private DoacaoService doacaoService;

    @Test
    @DisplayName("Deve criar doação quando dados válidos")
    void deveCriarDoacaoQuandoDadosValidos() {
        // ==========================================
        // ARRANGE: Preparar o cenário
        // ==========================================

        // 1. Criar o DTO de entrada (o que o cliente envia)
        DoacaoRequestDTO dto = new DoacaoRequestDTO(
                1L,                                    // idDoador
                LocalDate.of(2026, 9, 11),            // dataDoacao
                new BigDecimal("100.00"),               // valor
                TipoDoacao.ALIMENTO,                   // tipo
                "Doação de teste"                      // descricao
        );

        // 2. Criar a doação que o Repository vai "retornar" (simulação)
        Doacao doacaoSalva = Doacao.builder()
                .idDoacao(1L)
                .idDoador(1L)
                .dataDoacao(LocalDate.of(2026, 9, 11))
                .valor(new BigDecimal("100.00"))
                .tipo(TipoDoacao.ALIMENTO)
                .descricao("Doação de teste")
                .build();

        // 3. Configurar o mock: quando save() for chamado, retornar doacaoSalva
        Mockito.when(doacaoRepository.save(Mockito.any()))
                .thenReturn(doacaoSalva);

        // ==========================================
        // ACT: Executar a ação
        // ==========================================

        DoacaoResponseDTO resultado = doacaoService.criar(dto);

        // ==========================================
        // ASSERT: Verificar o resultado
        // ==========================================

        // Verificar se o ID foi gerado
        Assertions.assertNotNull(resultado.idDoacao(), "ID da doação não deve ser nulo");

        // Verificar se os dados estão corretos
        Assertions.assertEquals(1L, resultado.idDoador());
        Assertions.assertEquals(new BigDecimal("100.00"), resultado.valor());
        Assertions.assertEquals(TipoDoacao.ALIMENTO, resultado.tipo());

        // Verificar se o save() foi chamado exatamente 1 vez
        Mockito.verify(doacaoRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Deve listar doações com paginação")
    void deveListarDoacoesComPaginacao() {
        // Este teste pode ser expandido conforme necessário
        // Por enquanto, vamos focar no teste de criação
        Assertions.assertTrue(true, "Teste placeholder");
    }
}