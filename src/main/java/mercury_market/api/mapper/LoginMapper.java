package mercury_market.api.mapper;

import mercury_market.api.dto.request.LoginRequest;
import mercury_market.api.dto.response.LoginResponse;
import mercury_market.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    LoginResponse toDTO(Usuario usuario);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nome", ignore = true)
    @Mapping(target = "tipoUsuario", ignore = true)
    Usuario toEntity(LoginRequest loginRequest);
}
