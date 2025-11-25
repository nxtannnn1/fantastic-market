package mercury_market.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Usuario usuario; //Muitas avaliações pertencem a um usuário

    @ManyToOne
    @NotNull
    @JsonIgnore
    private Produto produto; //Muitas avaliações pertencem a um produto

    @Min(1)
    @Max(5)
    private int nota; // 1 a 5
    private String comentario;

    private LocalDateTime dataCriacao = LocalDateTime.now();

}
