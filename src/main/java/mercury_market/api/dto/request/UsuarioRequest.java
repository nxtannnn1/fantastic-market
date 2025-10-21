package mercury_market.api.dto.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import mercury_market.domain.enums.TipoUsuario;

@Getter
@Setter
public class UsuarioRequest {

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @Email
    @NotBlank(message = "Email não pode ser vazio")
    private String email;

    @NotBlank(message = "Senha não pode ser vazio")
    @Size(min = 8, max = 20, message = "Senha deve ter entre 8 e 20 caracteres")
    private String senha;

    @NotNull(message = "Tipo de usuário não pode ser nulo")
    private TipoUsuario tipoUsuario;
}
