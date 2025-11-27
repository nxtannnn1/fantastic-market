package mercury_market.api.mapper;

import mercury_market.api.dto.response.AvaliacaoResponse;
import mercury_market.api.dto.response.EditarAvaliacaoResponse;
import mercury_market.domain.model.Avaliacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface AvaliacaoMapper {

    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "produtoId", source = "produto.id")
    AvaliacaoResponse toDTO(Avaliacao avaliacao);

    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "produtoId", source = "produto.id")
    EditarAvaliacaoResponse editToDTO(Avaliacao avaliacao);

}
