package br.com.socialconnect.api.beneficiarios.controller;

import br.com.socialconnect.api.beneficiarios.dto.BeneficiarioRequestDTO;
import br.com.socialconnect.api.beneficiarios.dto.BeneficiarioResponseDTO;
import br.com.socialconnect.api.beneficiarios.service.BeneficiarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/beneficiarios")
public class BeneficiarioController {

    private final BeneficiarioService service;

    public BeneficiarioController(BeneficiarioService service) {
        this.service = service;
    }

    // ✅ Chamando o método "listar" com os 3 parâmetros exatos
    @GetMapping
    public ResponseEntity<Page<BeneficiarioResponseDTO>> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf,
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {

        return ResponseEntity.ok(service.listar(nome, cpf, pageable));
    }

    @GetMapping("/{idBeneficiario}")
    public ResponseEntity<BeneficiarioResponseDTO> buscarPorId(@PathVariable Long idBeneficiario) {
        return ResponseEntity.ok(service.buscarPorId(idBeneficiario));
    }

    @PostMapping
    public ResponseEntity<BeneficiarioResponseDTO> criar(@Valid @RequestBody BeneficiarioRequestDTO dto) {
        BeneficiarioResponseDTO salvo = service.criar(dto);
        URI location = URI.create("/api/v1/beneficiarios/" + salvo.idBeneficiario());
        return ResponseEntity.created(location).body(salvo);
    }

    @DeleteMapping("/{idBeneficiario}")
    public ResponseEntity<Void> deletar(@PathVariable Long idBeneficiario) {
        service.deletar(idBeneficiario);
        return ResponseEntity.noContent().build();
    }
}