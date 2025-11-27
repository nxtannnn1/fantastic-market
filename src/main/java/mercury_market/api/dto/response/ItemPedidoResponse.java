package mercury_market.api.dto.response;

import java.math.BigDecimal;

public record ItemPedidoResponse(
        Long id,
        Long pedidoId,
        Long produtoId,
        Integer quantidade,
        BigDecimal precoUnitario
) {
}
