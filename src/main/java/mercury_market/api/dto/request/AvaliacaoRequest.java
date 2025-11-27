package mercury_market.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AvaliacaoRequest(

        @NotNull
        Long clienteId,
        @NotNull
        Long produtoId,
        @NotNull
        @Min(1)
        @Max(5)
        Integer nota,
        String comentario

) {
}
