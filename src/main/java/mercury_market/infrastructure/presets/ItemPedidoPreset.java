package mercury_market.infrastructure.presets;

import jakarta.transaction.Transactional;
import mercury_market.domain.model.ItemPedido;
import mercury_market.infrastructure.repository.ItemPedidoRepository;
import mercury_market.infrastructure.repository.PedidoRepository;
import mercury_market.infrastructure.repository.ProdutoRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(5)
@Component
public class ItemPedidoPreset {

    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public ItemPedidoPreset(ItemPedidoRepository itemPedidoRepository, PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;

    }

    @Transactional
    public void criarItemPedidosPadrao() {

        if (itemPedidoRepository.count() == 0) {

            ItemPedido itemPedido = new ItemPedido();

            var pedido = pedidoRepository.findById(1L).orElseThrow(() -> new RuntimeException("Teste"));
            var produto = produtoRepository.findById(1L).orElseThrow(() -> new RuntimeException("Teste"));
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(1);
            itemPedido.setPrecoUnitario(pedido.getValorTotal());


            itemPedidoRepository.save(itemPedido);

        }
    }
}
