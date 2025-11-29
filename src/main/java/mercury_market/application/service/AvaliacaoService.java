package mercury_market.application.service;

import mercury_market.api.dto.request.AvaliacaoRequest;
import mercury_market.api.dto.request.EditarAvaliacaoRequest;
import mercury_market.api.dto.response.AvaliacaoResponse;
import mercury_market.api.dto.response.EditarAvaliacaoResponse;
import mercury_market.api.mapper.AvaliacaoMapper;
import mercury_market.domain.model.Avaliacao;
import mercury_market.exceptions.AvaliacaoNaoEncontradaException;
import mercury_market.exceptions.ProdutoNaoEncontradoException;
import mercury_market.exceptions.UsuarioNaoEncontradoException;
import mercury_market.infrastructure.repository.AvaliacaoRepository;
import mercury_market.infrastructure.repository.ProdutoRepository;
import mercury_market.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final AvaliacaoMapper avaliacaoMapper;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, AvaliacaoMapper avaliacaoMapper, UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.avaliacaoMapper = avaliacaoMapper;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
    }

    public AvaliacaoResponse avaliarProduto(AvaliacaoRequest avaliacaoRequest) {

        Avaliacao avaliacao = new Avaliacao();

        var cliente = usuarioRepository.findById(avaliacaoRequest.usuarioId()).orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado!"));
        var produto = produtoRepository.findById(avaliacaoRequest.produtoId()).orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado!"));
        avaliacao.setUsuario(cliente);
        avaliacao.setProduto(produto);
        avaliacao.setNota(avaliacaoRequest.nota());
        avaliacao.setComentario(avaliacaoRequest.comentario());
        avaliacao.setDataCriacao(LocalDateTime.now());
        avaliacaoRepository.save(avaliacao);
        return avaliacaoMapper.toDTO(avaliacao);
    }

    public EditarAvaliacaoResponse editarAvaliacao(EditarAvaliacaoRequest avaliacaoRequest, Long id) {

        var avaliacao = avaliacaoRepository.findById(id).orElseThrow(() -> new AvaliacaoNaoEncontradaException("Avaliação de ID " + id + " não encontrada"));
        avaliacao.setNota(avaliacaoRequest.nota());
        avaliacao.setComentario(avaliacaoRequest.comentario());
        avaliacaoRepository.save(avaliacao);
        return avaliacaoMapper.editToDTO(avaliacao);
    }

    public AvaliacaoResponse listarAvaliacaoPorId(Long id) {
        return avaliacaoMapper.toDTO(avaliacaoRepository.findById(id).orElseThrow(() -> new AvaliacaoNaoEncontradaException("Avaliação de ID " + id + " não encontrada")));
    }

}
