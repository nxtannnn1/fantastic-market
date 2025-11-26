package mercury_market.api.controller;

import mercury_market.api.dto.request.PagamentoRequest;
import mercury_market.api.dto.response.PagamentoResponse;
import mercury_market.application.service.PagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamentos")
@CrossOrigin("[*]")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService){
        this.pagamentoService=pagamentoService;
    }

    public ResponseEntity<PagamentoResponse> processarPagamento(PagamentoRequest pagamentoRequest ){
        var pagamento = pagamentoService.processsarPagamento(pagamentoRequest);
        return ResponseEntity.status(HttpStatus.OK).body(pagamento);
    }
}
