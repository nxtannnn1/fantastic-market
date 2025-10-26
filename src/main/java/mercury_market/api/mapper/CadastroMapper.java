package mercury_market.api.mapper;

import mercury_market.api.dto.request.CadastroRequest;
import mercury_market.api.dto.response.CadastroResponse;
import mercury_market.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface CadastroMapper {
    CadastroResponse toDTO(Usuario usuario);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipoUsuario", ignore = true)
    Usuario toEntity(CadastroRequest cadastroRequest);

}
