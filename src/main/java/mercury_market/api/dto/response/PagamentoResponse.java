package mercury_market.api.dto.response;

import java.time.LocalDateTime;

public record PagamentoResponse(
        Long id,
        Long usuarioId,
        Long pedidoId,
        String tipoPagamento,
        LocalDateTime dataPagamento
) {
}
