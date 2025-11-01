package mercury_market.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mercury_market.domain.enums.Categoria;

import java.math.BigDecimal;

@Embeddable
@Data
public class DetalhesProdutos {

    @Column(name = "nome_produto",
            nullable = false)
    @NotBlank(message = "Nome do produto não pode ser vazio")
    private String nome;

    @NotNull(message = "Preço do produto não pode ser nulo")
    @DecimalMin(value = "0.1", message = "Preço mínimo é R$ 0,10")
    @Column(name = "preco_produto",
            nullable = false,
            scale = 2,
            precision = 10)
    private BigDecimal preco;

    @NotNull(message = "Quantidade do produto não pode ser nula")
    @Column(name = "quantidade_produto",
            nullable = false)
    @Min(value = 0, message = "Quantidade não pode ser inferior a zero")
    private Integer quantidade;

    @Column(name = "marca_produto",
            nullable = false)
    private String marca;

    @NotNull(message = "Categoria não pode ser nula")
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_produto",
            nullable = false)
    private Categoria categoria;

    @Column(name = "descricao_produto")
    private String descricao;

    @Column(name = "url_imagem")
    private String urlImagem;
}
