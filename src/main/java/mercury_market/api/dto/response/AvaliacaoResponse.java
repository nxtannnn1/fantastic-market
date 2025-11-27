package mercury_market.api.dto.response;

import java.time.LocalDateTime;

public record AvaliacaoResponse(
        Long id,
        Long usuarioId,
        Long produtoId,
        LocalDateTime dataCriacao,
        String comentario,
        Integer nota
) {
}
