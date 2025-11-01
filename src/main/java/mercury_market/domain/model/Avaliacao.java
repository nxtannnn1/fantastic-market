package mercury_market.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario; //Muitas avaliações pertencem a um usuário

    @ManyToOne
    private Produto produto; //Muitas avaliações pertencem a um produto

    private int nota; // 1 a 5
    private String comentario;

    private LocalDateTime dataCriacao = LocalDateTime.now();

}
