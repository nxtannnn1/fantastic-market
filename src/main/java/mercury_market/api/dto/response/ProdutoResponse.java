package mercury_market.api.dto.response;

import mercury_market.api.dto.request.AvaliacaoRequest;
import mercury_market.domain.enums.Categoria;
import mercury_market.domain.model.Avaliacao;

import java.math.BigDecimal;
import java.util.List;

public record ProdutoResponse(Long id,
                              String nome,
                              BigDecimal preco,
                              Integer quantidade,
                              String descricao,
                              String marca,
                              Categoria categoria,
                              String urlImagem,
                              List<AvaliacaoResponse> avaliacoes) {

}

