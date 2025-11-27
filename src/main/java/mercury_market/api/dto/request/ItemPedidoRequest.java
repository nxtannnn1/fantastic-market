package mercury_market.api.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemPedidoRequest(
        @NotNull
        Long pedidoId,
        @NotNull
        Long produtoId,
        @NotNull
        @Min(1)
        Integer quantidade,
        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal precoUnitario
) {
}
