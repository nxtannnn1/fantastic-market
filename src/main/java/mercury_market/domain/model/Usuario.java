package mercury_market.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mercury_market.domain.enums.TipoUsuario;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode ser vazio")
    @Column(name = "nome_usuario",
            nullable = false)
    private String nome;

    @Column(name = "email_usuario",
            unique = true,
            nullable = false)
    @Email
    @NotBlank(message = "Email não pode ser vazio")
    private String email;

    @Column(name = "senha_usuario",
            nullable = false)
    @NotBlank(message = "Senha não pode ser vazio")
    @Size(min = 8, max = 20, message = "Senha deve ter entre 8 e 20 caracteres")
    private String senha;

    @Column(name = "tipo_usuario",
            nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Tipo de usuário não pode ser nulo")
    private TipoUsuario tipoUsuario;
}
