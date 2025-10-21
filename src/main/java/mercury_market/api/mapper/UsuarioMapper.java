package mercury_market.api.mapper;

import mercury_market.api.dto.request.UsuarioRequest;
import mercury_market.api.dto.response.UsuarioResponse;
import mercury_market.domain.model.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioResponse toDTO(Usuario usuario);
    Usuario toEntity(UsuarioRequest usuarioRequest);
    List<UsuarioResponse> toDTO(List<Usuario> usuarios);
}
