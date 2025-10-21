package mercury_market.api.mapper;

import mercury_market.api.dto.request.ProdutoRequest;
import mercury_market.api.dto.response.ProdutoResponse;
import mercury_market.domain.model.Produto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoResponse toDTO(Produto produto);

    Produto toEntity(ProdutoRequest produtoRequest);

    List<ProdutoResponse> toDTO(List<Produto> produtos);

}
