package br.com.socialconnect.api.doacoes.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "doacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doacao") // Padrão id_ obrigatório
    private Long idDoacao;

    @Column(name = "id_doador", nullable = false)
    private Long idDoador; // Simplificado como Long para focar no filtro

    @Column(name = "data_doacao", nullable = false)
    private LocalDate dataDoacao;

    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private TipoDoacao tipo;

    @Column(length = 500)
    private String descricao;
}