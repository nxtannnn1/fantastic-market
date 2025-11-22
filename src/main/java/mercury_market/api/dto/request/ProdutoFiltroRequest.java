package mercury_market.api.dto.request;

import mercury_market.domain.enums.Categoria;

public record ProdutoFiltroRequest (String nome, Categoria categoria){
}
