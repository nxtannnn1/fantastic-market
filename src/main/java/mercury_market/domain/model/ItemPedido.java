package mercury_market.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

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

    private Integer quantidade;
    private BigDecimal precoUnitario;

    public BigDecimal getSubTotal() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
}
