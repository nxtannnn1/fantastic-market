package mercury_market.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario; //Muitas avaliações pertencem a um usuário

    @ManyToOne
    @JsonIgnore
    private Produto produto; //Muitas avaliações pertencem a um produto

    private int nota; // 1 a 5
    private String comentario;

    private LocalDateTime dataCriacao = LocalDateTime.now();

}
