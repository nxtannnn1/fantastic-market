package mercury_market.api.controller;

import jakarta.validation.Valid;
import mercury_market.api.dto.request.AvaliacaoRequest;
import mercury_market.api.dto.response.AvaliacaoResponse;
import mercury_market.application.service.AvaliacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes")
@CrossOrigin(origins = "*")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping("/avaliar")
    public ResponseEntity<AvaliacaoResponse> avaliarProduto(@RequestBody @Valid AvaliacaoRequest avaliacaoRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(avaliacaoService.avaliarProduto(avaliacaoRequest));

    }
}
