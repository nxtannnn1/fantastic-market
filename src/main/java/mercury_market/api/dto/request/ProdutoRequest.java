package mercury_market.api.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mercury_market.api.dto.response.AvaliacaoResponse;
import mercury_market.domain.enums.Categoria;

import java.math.BigDecimal;
import java.util.List;

public record ProdutoRequest(

        @NotBlank(message = "Nome do produto não pode ser vazio")
        String nome,

        @NotNull(message = "Preço do produto não pode ser nulo")
        @DecimalMin(value = "0.1", message = "Preço mínimo é R$ 0,10")
        BigDecimal preco,

        @NotNull(message = "Quantidade do produto não pode ser nula")
        @Min(value = 0, message = "Quantidade não pode ser inferior a zero")
        Integer quantidade,

        String descricao,

        String marca,

        @NotNull(message = "Categoria não pode ser nula")
        Categoria categoria,

        String urlImagem
) {

}
