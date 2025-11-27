package mercury_market.infrastructure.presets;

import jakarta.transaction.Transactional;
import mercury_market.domain.enums.Categoria;
import mercury_market.domain.model.Avaliacao;
import mercury_market.domain.model.DetalhesProdutos;
import mercury_market.domain.model.Produto;
import mercury_market.exceptions.UsuarioNaoEncontradoException;
import mercury_market.infrastructure.repository.ProdutoRepository;
import mercury_market.infrastructure.repository.UsuarioRepository;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@DependsOn("usuarioPreset")
@Component
public class ProdutoPreset {

    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public ProdutoPreset(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository) {
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void criarProdutosPadrao() {

        var usuario = usuarioRepository.findByEmail("cliente@mm.com").orElseThrow(()-> new UsuarioNaoEncontradoException("Usuário não encontradoi!"));

        var produto = new Produto();
        var detalhes = new DetalhesProdutos();
        var avaliacao = new Avaliacao();

        var produto2 = new Produto();
        var detalhes2 = new DetalhesProdutos();

        var produto3 = new Produto();
        var detalhes3 = new DetalhesProdutos();

        if (produtoRepository.count() == 0) {

            detalhes.setNome("PC Gamer Dell G15");
            detalhes.setPreco(new BigDecimal("2500.00"));
            detalhes.setQuantidade(3);
            detalhes.setMarca("Dell");
            detalhes.setCategoria(Categoria.ELETRONICOS);
            detalhes.setDescricao("PC Gamer com Ryzen 5, 16GB RAM, RTX 3060 e SSD NVMe — desempenho sólido para jogos competitivos e multitarefas.");
            detalhes.setUrlImagem("null");
            produto.setDetalhesProdutos(detalhes);
            avaliacao.setUsuario(usuario);
            avaliacao.setProduto(produto);
            avaliacao.setNota(4);
            avaliacao.setComentario("Achei muito bom e otimizado para meus jogos online! Recomendo!");
            avaliacao.setDataCriacao(LocalDateTime.now());
            produto.getAvaliacoes().add(avaliacao);
            produtoRepository.save(produto);

            detalhes2.setNome("Smartphone Samsung Galaxy S22");
            detalhes2.setPreco(new BigDecimal("8500.00"));
            detalhes2.setQuantidade(2);
            detalhes2.setMarca("Samsung");
            detalhes2.setCategoria(Categoria.ELETRONICOS);
            detalhes2.setDescricao("Smartphone premium com câmera tripla de 50MP, tela AMOLED 120Hz e processador Snapdragon — ideal para quem exige alto desempenho.");
            detalhes2.setUrlImagem("null");
            produto2.setDetalhesProdutos(detalhes2);
            produtoRepository.save(produto2);

            detalhes3.setNome("Fone Bluetooth JBL Tune 520BT");
            detalhes3.setPreco(new BigDecimal("500.00"));
            detalhes3.setQuantidade(10);
            detalhes3.setMarca("Dell");
            detalhes3.setCategoria(Categoria.ELETRONICOS);
            detalhes3.setDescricao("Fone leve e confortável com graves potentes, bateria de até 57 horas e conectividade rápida — perfeito para treinos e trabalho.");
            detalhes3.setUrlImagem("null");
            produto3.setDetalhesProdutos(detalhes3);
            produtoRepository.save(produto3);

        }
    }

}
