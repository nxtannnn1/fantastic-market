package mercury_market.application.service;

import jakarta.transaction.Transactional;
import mercury_market.api.dto.request.ProdutoRequest;
import mercury_market.api.dto.response.ProdutoResponse;
import mercury_market.api.dto.response.UsuarioResponse;
import mercury_market.api.mapper.ProdutoMapper;
import mercury_market.infrastructure.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<ProdutoResponse> listarProdutos() {
        return produtoMapper.toDTO(produtoRepository.findAll());
    }

    public ProdutoResponse listarProdutoPorId(Long id){
        var produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException ("Produto de id "+id+" não encontrado!"));
        return produtoMapper.toDTO(produto);
    }

    @Transactional
    public ProdutoResponse editarProdutoPorId(Long id, ProdutoRequest produtoRequest){
        if(id==null) throw new RuntimeException("ID não pode ser nulo!");
        var produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto de ID "+id+" não encontrado no sistema!"));
        produto.getDetalhesProdutos().setNome(produtoRequest.getNome());
        produto.getDetalhesProdutos().setPreco(produtoRequest.getPreco());
        produto.getDetalhesProdutos().setQuantidade(produtoRequest.getQuantidade());
        produto.getDetalhesProdutos().setMarca(produtoRequest.getMarca());
        produto.getDetalhesProdutos().setCategoria(produtoRequest.getCategoria());
        produto.getDetalhesProdutos().setUrlImagem(produtoRequest.getUrlImagem());
        return produtoMapper.toDTO(produtoRepository.save(produto));
    }

    @Transactional
    public void excluirProdutoPorId(Long id){
        if(id==null) throw new RuntimeException("ID não pode ser nulo");
        var produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto de ID "+id+" não encontrado no sistema!"));
        produtoRepository.delete(produto);
    }
}
