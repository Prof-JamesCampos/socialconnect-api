package br.com.socialconnect.api.beneficiarios.controller;

import br.com.socialconnect.api.beneficiarios.dto.BeneficiarioRequestDTO;
import br.com.socialconnect.api.beneficiarios.dto.BeneficiarioResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BeneficiarioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
//    @Autowired
//    private ObjectMapper objectMapper; // Converte Java para JSON automaticamente

    private static Long idCriado;

    // ==========================================
    // TESTE 1: Criar beneficiário com sucesso
    // ==========================================
    @Test
    @Order(1)
    @DisplayName("POST - Deve criar beneficiário e retornar 201")
    void deveCriarBeneficiarioComSucesso() throws Exception {
        BeneficiarioRequestDTO dto = new BeneficiarioRequestDTO(
                "Maria da Silva",
                "12345678909",
                "11999999999",
                "Rua das Flores, 123",
                "Renda familiar baixa"
        );

        mockMvc.perform(post("/api/v1/beneficiarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated()) // Verifica se é 201
                .andExpect(jsonPath("$.idBeneficiario").exists()) // Verifica se retornou ID
                .andExpect(jsonPath("$.nome").value("Maria da Silva"))
                .andDo(result -> {
                    // Guarda o ID criado para usar nos próximos testes
                    String responseContent = result.getResponse().getContentAsString();
                    idCriado = objectMapper.readTree(responseContent).get("idBeneficiario").asLong();
                });
    }

    // ==========================================
    // TESTE 2: Buscar por ID
    // ==========================================
    @Test
    @Order(2)
    @DisplayName("GET - Deve buscar beneficiário por ID")
    void deveBuscarBeneficiarioPorId() throws Exception {
        mockMvc.perform(get("/api/v1/beneficiarios/" + idCriado))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()) // Verifica se é 200
                .andExpect(jsonPath("$.nome").value("Maria da Silva"));
    }

    // ==========================================
    // TESTE 3: Buscar por ID inexistente (404)
    // ==========================================
    @Test
    @Order(3)
    @DisplayName("GET - Deve retornar 404 quando ID não existe")
    void deveRetornar404QuandoIdNaoExiste() throws Exception {
        mockMvc.perform(get("/api/v1/beneficiarios/99999"))
                .andExpect(status().isNotFound()); // Verifica se é 404
    }

    // ==========================================
    // TESTE 4: Criar com dados inválidos (400)
    // ==========================================
    @Test
    @Order(4)
    @DisplayName("POST - Deve retornar 400 quando nome está vazio")
    void deveRetornar400QuandoNomeVazio() throws Exception {
        BeneficiarioRequestDTO dto = new BeneficiarioRequestDTO(
                "", // Nome vazio (inválido)
                "11122233344",
                "11999999999",
                null,
                null
        );

        mockMvc.perform(post("/api/v1/beneficiarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest()); // Verifica se é 400
    }

    // ==========================================
    // TESTE 5: Deletar beneficiário (204)
    // ==========================================
    @Test
    @Order(5)
    @DisplayName("DELETE - Deve deletar beneficiário e retornar 204")
    void deveDeletarBeneficiario() throws Exception {
        mockMvc.perform(delete("/api/v1/beneficiarios/" + idCriado))
                .andExpect(status().isNoContent()); // Verifica se é 204
    }
}