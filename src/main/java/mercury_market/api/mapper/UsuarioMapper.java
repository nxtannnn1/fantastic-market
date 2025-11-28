package mercury_market.api.mapper;

import mercury_market.api.dto.request.UsuarioRequest;
import mercury_market.api.dto.response.AlterarEmailUsuarioResponse;
import mercury_market.api.dto.response.AlterarSenhaUsuarioResponse;
import mercury_market.api.dto.response.UsuarioResponse;
import mercury_market.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioResponse toDTO(Usuario usuario);

    @Mapping(target = "id", ignore = true)
    Usuario toEntity(UsuarioRequest usuarioRequest);

    List<UsuarioResponse> toDTO(List<Usuario> usuarios);


    @Mapping(source = "email", target = "emailNovo")
    AlterarEmailUsuarioResponse toEmailDTO(Usuario usuario);

    @Mapping(source = "email", target = "email")
    @Mapping(target = "mensagem", ignore = true)
    AlterarSenhaUsuarioResponse toSenhaDTO(Usuario usuario);
}
