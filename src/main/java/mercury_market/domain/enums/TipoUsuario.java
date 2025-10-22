package mercury_market.domain.enums;

public enum TipoUsuario {

    ADM("Administrador"),
    VISITANTE("Visitante"),
    VENDEDOR("Vendedor");

    private final String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

}
