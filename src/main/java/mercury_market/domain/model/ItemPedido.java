package mercury_market.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Data
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido; //Muitos itens pertencem a um pedido

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @NotNull
    private Integer quantidade;
    @NotNull
    private BigDecimal precoUnitario;

    public BigDecimal getSubTotal() {
        if (precoUnitario == null || quantidade == null) return BigDecimal.ZERO;

        return precoUnitario
                .multiply(BigDecimal.valueOf(quantidade))
                .setScale(2, RoundingMode.HALF_UP);

    }
}
