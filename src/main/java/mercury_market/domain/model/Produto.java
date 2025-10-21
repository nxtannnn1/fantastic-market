package mercury_market.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Entity
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @NotNull(message = "Detalhes não devem ser nulos")
    private DetalhesProdutos detalhesProdutos;
}
