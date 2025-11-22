package mercury_market.application.service;

import jakarta.transaction.Transactional;
import mercury_market.api.dto.request.ProdutoFiltroRequest;
import mercury_market.api.dto.request.ProdutoRequest;
import mercury_market.api.dto.response.ProdutoResponse;
import mercury_market.api.mapper.ProdutoMapper;
import mercury_market.domain.model.DetalhesProdutos;
import mercury_market.domain.model.Produto;
import mercury_market.exceptions.ProdutoNaoEncontradoException;
import mercury_market.infrastructure.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    @Transactional
    public ProdutoResponse cadastrarProduto(ProdutoRequest dto) {
        var produto = produtoMapper.toEntity(dto);
        return produtoMapper.toDTO(produtoRepository.save(produto));
    }

    @Transactional
    public Page<ProdutoResponse> listarProdutos(Pageable pageable) {
        return produtoRepository.findAll(pageable).map(produtoMapper::toDTO);
    }

    public ProdutoResponse listarProdutoPorId(Long id) {
        var produto = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException("Produto de id " + id + " não encontrado!"));
        return produtoMapper.toDTO(produto);
    }

    @Transactional
    public void excluirProdutoPorId(Long id) {
        if (id == null) throw new IllegalArgumentException("ID não pode ser nulo");
        var produto = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException("Produto de ID " + id + " não encontrado no sistema!"));
        produtoRepository.delete(produto);
    }

    @Transactional
    public ProdutoResponse atualizarProduto(Long id, ProdutoRequest dto) {
        if (id == null) throw new IllegalArgumentException("ID não pode ser nulo");
        var produto = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException("Produto de ID " + id + " não encontrado no sistema!"));
        atualizarDetalhes(produto.getDetalhesProdutos(), dto);
        return produtoMapper.toDTO(produtoRepository.save(produto));
    }

    @Transactional
    public Page<ProdutoResponse> filtrarProdutos(ProdutoFiltroRequest dto, Pageable pageable) {

        Page<Produto> produtos;

        if (dto.nome() != null && dto.categoria() != null) {
            produtos = produtoRepository.findByDetalhesProdutosNomeContainingIgnoreCaseAndDetalhesProdutosCategoria(dto.nome(), dto.categoria(), pageable);
        }

        // Filtrar por categoria
        else if (dto.categoria() != null) {
            produtos = produtoRepository.findByDetalhesProdutosCategoria(
                    dto.categoria(), pageable);
        }

        // Filtrar por nome parcial
        else if (dto.nome() != null) {
            produtos = produtoRepository.findByDetalhesProdutosNomeContainingIgnoreCase(
                    dto.nome(), pageable);
        }

        //Filtrar por nome e categoria

        else {
            produtos = produtoRepository.findAll(pageable); // inicializa com todos se nenhum filtro
        }

        return produtos.map(produtoMapper::toDTO);
    }

    public void atualizarDetalhes(DetalhesProdutos detalhes, ProdutoRequest dto) {

        if (dto.nome() != null) detalhes.setNome(dto.nome());
        if (dto.preco() != null) detalhes.setPreco(dto.preco());
        if (dto.quantidade() != null) detalhes.setQuantidade(dto.quantidade());
        if (dto.marca() != null) detalhes.setMarca(dto.marca());
        if (dto.categoria() != null) detalhes.setCategoria(dto.categoria());
        if (dto.urlImagem() != null) detalhes.setUrlImagem(dto.urlImagem());
    }
}
