package mercury_market.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import mercury_market.domain.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable=false)
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido =StatusPedido.PENDENTE;

    @Column(nullable=false)
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @PrePersist
    @PreUpdate
    public void calcularValorTotal() {
        this.valorTotal = itens.stream()
                .map(ItemPedido::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
