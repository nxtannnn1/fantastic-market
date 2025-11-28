package mercury_market.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AlterarSenhaUsuarioRequest(
        @NotBlank
        @Min(6)
        @Max(20)
        String senhaAntiga,
        @NotBlank
        @Min(6)
        @Max(20)
        String senhaNova
) {
}
