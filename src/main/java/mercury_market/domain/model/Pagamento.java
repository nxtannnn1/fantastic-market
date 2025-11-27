package mercury_market.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import mercury_market.domain.enums.TipoPagamento;

import java.time.LocalDateTime;

@Data
@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "usuario_id", nullable = false)
    @ManyToOne
    private Usuario cliente;

    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false, unique = true)
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPagamento tipoPagamento;

    private LocalDateTime dataPagamento = LocalDateTime.now();
}
