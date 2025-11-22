package mercury_market.api.dto.response;

import mercury_market.domain.enums.Categoria;

public record ProdutoFiltroResponse(String nome, Categoria categoria) {
}
