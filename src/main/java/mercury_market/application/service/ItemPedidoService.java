package mercury_market.application.service;

import jakarta.transaction.Transactional;
import mercury_market.api.dto.request.ItemPedidoRequest;
import mercury_market.api.dto.response.ItemPedidoResponse;
import mercury_market.api.mapper.ItemPedidoMapper;
import mercury_market.domain.model.ItemPedido;
import mercury_market.exceptions.PedidoNaoEncontradoException;
import mercury_market.exceptions.ProdutoNaoEncontradoException;
import mercury_market.infrastructure.repository.ItemPedidoRepository;
import mercury_market.infrastructure.repository.PedidoRepository;
import mercury_market.infrastructure.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoMapper itemPedidoMapper;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository, PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, ItemPedidoMapper itemPedidoMapper) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.itemPedidoMapper = itemPedidoMapper;

    }

    @Transactional
    public ItemPedidoResponse criarItemPedido(ItemPedidoRequest itemPedidoRequest) {
        ItemPedido itemPedido = new ItemPedido();
        var pedido = pedidoRepository.findById(itemPedidoRequest.pedidoId()).orElseThrow(() -> new PedidoNaoEncontradoException("Erro ao processar o pedido de id " + itemPedidoRequest.pedidoId()));
        var produto = produtoRepository.findById(itemPedidoRequest.produtoId()).orElseThrow(() -> new ProdutoNaoEncontradoException("Erro ao processar o produto de id " + itemPedidoRequest.produtoId()));

        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(itemPedidoRequest.quantidade());
        itemPedido.setPrecoUnitario(itemPedidoRequest.precoUnitario());

        itemPedidoRepository.save(itemPedido);

        return itemPedidoMapper.toDTO(itemPedido);

    }


}
