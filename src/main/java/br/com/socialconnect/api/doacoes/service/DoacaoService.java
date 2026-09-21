package br.com.socialconnect.api.doacoes.service;

import br.com.socialconnect.api.doacoes.dto.DoacaoRequestDTO;
import br.com.socialconnect.api.doacoes.dto.DoacaoResponseDTO;
import br.com.socialconnect.api.doacoes.model.Doacao;
import br.com.socialconnect.api.doacoes.model.TipoDoacao;
import br.com.socialconnect.api.doacoes.repository.DoacaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class DoacaoService {

    private final DoacaoRepository repository;

    public DoacaoService(DoacaoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public DoacaoResponseDTO criar(DoacaoRequestDTO dto) {
        Doacao doacao = toEntity(dto);
        Doacao salva = repository.save(doacao);
        return toDTO(salva);
    }

    public Page<DoacaoResponseDTO> listar(
            LocalDate dataInicio, LocalDate dataFim, TipoDoacao tipo, Pageable pageable) {

        Page<Doacao> page;

        if (dataInicio != null && dataFim != null && tipo != null) {
            page = repository.findByDataDoacaoBetweenAndTipo(dataInicio, dataFim, tipo, pageable);
        } else {
            page = repository.findAll(pageable);
        }

        return page.map(this::toDTO);
    }

    private Doacao toEntity(DoacaoRequestDTO dto) {
        Doacao doacao = new Doacao();
        // Ajuste os getters/setters conforme os campos reais da sua entidade Doacao
        doacao.setIdDoador(dto.idDoador()); // ou dto.getIdDoador() se for classe comum
        doacao.setDataDoacao(dto.dataDoacao());
        doacao.setValor(dto.valor());
        doacao.setTipo(dto.tipo());
        return doacao;
    }

    private DoacaoResponseDTO toDTO(Doacao entity) {
        return new DoacaoResponseDTO(
                entity.getIdDoacao(),
                entity.getIdDoador(),
                entity.getDataDoacao(),
                entity.getValor(),
                entity.getTipo()
        );
    }
}