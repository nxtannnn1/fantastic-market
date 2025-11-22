package mercury_market.api.mapper;

import mercury_market.api.dto.request.LoginRequest;
import mercury_market.api.dto.response.LoginResponse;
import mercury_market.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    @Mapping(target = "token", source = "token")
    LoginResponse toDTO(Usuario usuario, String token);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nome", ignore = true)
    @Mapping(target = "tipoUsuario", ignore = true)
        //@Mapping(target = "token", ignore = true)
    Usuario toEntity(LoginRequest loginRequest);
}
