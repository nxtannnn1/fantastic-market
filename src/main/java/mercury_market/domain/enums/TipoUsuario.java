package mercury_market.domain.enums;

import lombok.Getter;

@Getter
public enum TipoUsuario {

    ADM("Administrador"),
    CLIENTE("Visitante"),
    VENDEDOR("Vendedor");

    private final String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

}
