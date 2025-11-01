package mercury_market.domain.model;

import java.time.LocalDateTime;
import mercury_market.domain.enums.TipoPagamento;

public class Pagamento {

    private Long id;

    private Usuario cliente;

    private TipoPagamento tipoPagamento;

    private LocalDateTime dataPagamento = LocalDateTime.now();
}
