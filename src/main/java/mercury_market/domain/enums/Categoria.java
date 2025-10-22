package mercury_market.domain.enums;

public enum Categoria {
    BEBIDAS("Bebidas"),
    ELETRONICOS("Eletrônicos");

    private final String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }


}
