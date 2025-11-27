package mercury_market.api.mapper;

import mercury_market.api.dto.response.ItemPedidoResponse;
import mercury_market.domain.model.ItemPedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {
    @Mapping(source = "pedido.id", target = "pedidoId")
    @Mapping(source = "produto.id", target = "produtoId")
    ItemPedidoResponse toDTO(ItemPedido ItemPedido);
}
