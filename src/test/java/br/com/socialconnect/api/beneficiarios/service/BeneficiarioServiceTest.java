package br.com.socialconnect.api.beneficiarios.service;

import br.com.socialconnect.api.beneficiarios.dto.BeneficiarioResponseDTO;
import br.com.socialconnect.api.beneficiarios.model.Beneficiario;
import br.com.socialconnect.api.beneficiarios.repository.BeneficiarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BeneficiarioServiceTest {  // ← Note o nome diferente: ServiceTest

    @Mock
    private BeneficiarioRepository repository;  // ← Mock (simulação)

    @InjectMocks
    private BeneficiarioService service;  // ← Importa o código de PRODUÇÃO

    @Test
    void deveRetornarBeneficiarioQuandoIdExistir() {
        // Arrange
        Long id = 1L;
        Beneficiario beneficiario = Beneficiario.builder()
                .idBeneficiario(id)
                .nome("Maria")
                .cpf("12345678909")
                .build();

        Mockito.when(repository.findById(id))
                .thenReturn(Optional.of(beneficiario));

        // Act
        BeneficiarioResponseDTO result = service.buscarPorId(id);

        // Assert
        Assertions.assertEquals("Maria", result.nome());
    }
}