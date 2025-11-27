package mercury_market.infrastructure.presets;

import jakarta.transaction.Transactional;
import mercury_market.domain.enums.StatusPedido;
import mercury_market.domain.model.ItemPedido;
import mercury_market.domain.model.Pedido;
import mercury_market.exceptions.ProdutoNaoEncontradoException;
import mercury_market.exceptions.UsuarioNaoEncontradoException;
import mercury_market.infrastructure.repository.PedidoRepository;
import mercury_market.infrastructure.repository.ProdutoRepository;
import mercury_market.infrastructure.repository.UsuarioRepository;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@DependsOn({"usuarioPreset", "produtoPreset"})
public class PedidoPreset {

    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;

    public PedidoPreset(UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public void criarPedidosPadrao() {

        if (pedidoRepository.count() == 0) {

            var usuario = usuarioRepository.findById(1L).orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado!"));
            var produto = produtoRepository.findById(1L).orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado!"));

            //Criação do pedido

            var pedido = new Pedido();
            pedido.setUsuario(usuario);
            pedido.setDataCriacao(LocalDateTime.now());
            pedido.setStatusPedido(StatusPedido.PENDENTE);

            //Item do pedido

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(1);
            itemPedido.setPrecoUnitario(produto.getDetalhesProdutos().getPreco());
            itemPedido.setPedido(pedido);

            pedido.getItens().add(itemPedido);

            pedidoRepository.save(pedido);

        }
    }
}
