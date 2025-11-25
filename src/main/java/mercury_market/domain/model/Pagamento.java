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

        @JoinColumn(name = "usuario_id")
        @ManyToOne
        private Usuario cliente;

        @JoinColumn(name = "pedido_id")
        @OneToOne
        private Pedido pedido;

        @Enumerated(EnumType.STRING)
        private TipoPagamento tipoPagamento;

        private LocalDateTime dataPagamento = LocalDateTime.now();
    }
