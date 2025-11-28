package mercury_market.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AlterarEmailUsuarioRequest(
        @Email
        @NotBlank
        String emailAntigo,
        @Email
        @NotBlank
        String emailNovo,
        @NotBlank
        @Size(min = 8, max=20, message = "Senha deve ter entre 8 e 20 caracteres")
        String senha
) {
}
