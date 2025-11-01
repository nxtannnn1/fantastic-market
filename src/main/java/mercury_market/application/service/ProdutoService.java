package mercury_market.application.service;

import jakarta.transaction.Transactional;
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
    public Page<ProdutoResponse> filtrarProdutos(ProdutoRequest dto, Pageable pageable) {

        Page<Produto> produtos;

        if (dto.getNome() != null && dto.getCategoria() != null) {
            produtos = produtoRepository.findByDetalhesProdutosNomeContainingIgnoreCaseAndDetalhesProdutosCategoria(dto.getNome(), dto.getCategoria(), pageable);
        }

        // Filtrar por categoria
        else if (dto.getCategoria() != null) {
            produtos = produtoRepository.findByDetalhesProdutosCategoria(
                    dto.getCategoria(), pageable);
        }

        // Filtrar por nome parcial
        else if (dto.getNome() != null) {
            produtos = produtoRepository.findByDetalhesProdutosNomeContainingIgnoreCase(
                    dto.getNome(), pageable);
        }

        //Filtrar por nome e categoria

        else {
            produtos = produtoRepository.findAll(pageable); // inicializa com todos se nenhum filtro
        }

        return produtos.map(produtoMapper::toDTO);
    }

    public void atualizarDetalhes(DetalhesProdutos detalhes, ProdutoRequest dto) {

        if (dto.getNome() != null) detalhes.setNome(dto.getNome());
        if (dto.getPreco() != null) detalhes.setPreco(dto.getPreco());
        if (dto.getQuantidade() != null) detalhes.setQuantidade(dto.getQuantidade());
        if (dto.getMarca() != null) detalhes.setMarca(dto.getMarca());
        if (dto.getCategoria() != null) detalhes.setCategoria(dto.getCategoria());
        if (dto.getUrlImagem() != null) detalhes.setUrlImagem(dto.getUrlImagem());
    }
}
