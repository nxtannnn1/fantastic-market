package mercury_market.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroRequest(
        @NotBlank
        @Size(min = 4, max = 20, message = "Nome deve ter enter 6 e 20 caracteres")
        String nome,
        @NotBlank(message = "Email do usuário não pode ser vazio")
        String email,
        @NotBlank(message = "Senha do usuário não pode ser vazio")
        @Size(min = 8, max=20, message = "Senha deve ter entre 8 e 20 caracteres")
        String senha
) {
}
