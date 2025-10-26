package mercury_market.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroRequest {
    @NotBlank
    @Size(min = 4, max = 20, message = "Nome deve ter enter 6 e 20 caracteres")
    private String nome;
    @NotBlank(message = "Email do usuário não pode ser vazio")
    private String email;
    @NotBlank(message = "Senha do usuário não pode ser vazio")
    @Size(min = 6, max = 20, message = "Senha deve ter enter 6 e 20 caracteres")
    private String senha;
}
