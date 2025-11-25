package mercury_market.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PagamentoRequest(
        @NotNull
        Long pedidoId,

        @NotNull
        Long clienteId,

        @NotBlank(message = "O tipo de pagamento deve estar vinculado!")
        String tipoPagamento
) {


}
