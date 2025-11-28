package mercury_market.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AlterarSenhaUsuarioRequest(
        @NotBlank
        @Size(min = 8, max = 20, message = "Senha deve ter entre 8 e 20 caracteres")
        String senhaAntiga,
        @NotBlank
        @Size(min = 8, max = 20, message = "Senha deve ter entre 8 e 20 caracteres")
        String senhaNova,
        @NotBlank
        String email
) {
}
