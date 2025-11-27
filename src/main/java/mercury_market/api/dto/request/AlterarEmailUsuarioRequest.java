package mercury_market.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AlterarEmailUsuarioRequest(
        @Email
        @NotBlank
        String emailAntigo,
        @Email
        @NotBlank
        String emailNovo,
        @NotBlank
        String senha
) {
}
