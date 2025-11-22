package mercury_market.api.controller;

import jakarta.validation.Valid;
import mercury_market.api.dto.request.ProdutoFiltroRequest;
import mercury_market.api.dto.request.ProdutoRequest;
import mercury_market.api.dto.response.ProdutoResponse;
import mercury_market.application.service.ProdutoService;
import mercury_market.domain.enums.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrarProduto(@Valid @RequestBody ProdutoRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.cadastrarProduto(dto));
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoResponse>> listarProdutos(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.listarProdutos(pageable));
    }

    @GetMapping("/filtrar")
    public ResponseEntity<Page<ProdutoResponse>> filtrarProdutos(@RequestParam(required = false) String nome,
                                                                 @RequestParam(required = false) Categoria categoria,
                                                                 Pageable pageable) {
        ProdutoFiltroRequest dto = new ProdutoFiltroRequest(nome, categoria);
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.filtrarProdutos(dto, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> listarProdutoPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.listarProdutoPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProdutoPorId(@PathVariable Long id) {
        produtoService.excluirProdutoPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@PathVariable Long id, @RequestBody @Valid ProdutoRequest dto) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.atualizarProduto(id, dto));
    }
}
