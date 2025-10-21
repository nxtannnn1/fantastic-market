package mercury_market.api.dto.response;

import lombok.Data;
import mercury_market.domain.enums.TipoUsuario;

@Data
public class UsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    private TipoUsuario tipoUsuario;
}
