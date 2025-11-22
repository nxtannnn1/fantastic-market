package mercury_market.api.dto.response;

import mercury_market.domain.enums.TipoUsuario;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        TipoUsuario tipoUsuario
) {

}
