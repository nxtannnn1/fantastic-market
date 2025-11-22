package mercury_market.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import mercury_market.domain.enums.TipoPagamento;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "usuario_id")
    @ManyToOne
    private Usuario cliente;

    private TipoPagamento tipoPagamento;

    private LocalDateTime dataPagamento = LocalDateTime.now();
}
