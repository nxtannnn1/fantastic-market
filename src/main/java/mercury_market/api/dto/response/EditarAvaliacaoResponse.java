package mercury_market.api.dto.response;

import java.time.LocalDateTime;

public record EditarAvaliacaoResponse (
        Long id,
        Long clienteId,
        Long produtoId,
        Integer nota,
        String comentario,
        LocalDateTime dataCriacao
)
{
}
