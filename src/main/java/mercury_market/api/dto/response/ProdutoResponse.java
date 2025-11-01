package mercury_market.api.dto.response;

import lombok.Data;
import mercury_market.domain.enums.Categoria;

import java.math.BigDecimal;

@Data
public class ProdutoResponse {

    private Long id;

    private String nome;

    private BigDecimal preco;

    private Integer quantidade;

    private String descricao;

    private String marca;

    private Categoria categoria;

    private String urlImagem;
}
