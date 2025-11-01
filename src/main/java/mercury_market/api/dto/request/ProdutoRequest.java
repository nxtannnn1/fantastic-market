package mercury_market.api.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import mercury_market.domain.enums.Categoria;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoRequest {

    @NotBlank(message = "Nome do produto não pode ser vazio")
    private String nome;

    @NotNull(message = "Preço do produto não pode ser nulo")
    @DecimalMin(value = "0.1", message = "Preço mínimo é R$ 0,10")
    private BigDecimal preco;

    @NotNull(message = "Quantidade do produto não pode ser nula")
    @Min(value = 0, message = "Quantidade não pode ser inferior a zero")
    private Integer quantidade;

    private String descricao;

    private String marca;

    @NotNull(message = "Categoria não pode ser nula")
    private Categoria categoria;

    private String urlImagem;
}
