package mercury_market.api.mapper;

import mercury_market.api.dto.response.AvaliacaoResponse;
import mercury_market.api.dto.response.EditarAvaliacaoResponse;
import mercury_market.domain.model.Avaliacao;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface AvaliacaoMapper {
    AvaliacaoResponse toDTO(Avaliacao avaliacao);

}
