package mercury_market.api.mapper;

import mercury_market.api.dto.response.PagamentoResponse;
import mercury_market.domain.model.Pagamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface PagamentoMapper {

    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "pedidoId", source = "pedido.id")
    @Mapping(target = "tipoPagamento", source = "tipoPagamento")
    PagamentoResponse toDTO(Pagamento pagamento);
}
