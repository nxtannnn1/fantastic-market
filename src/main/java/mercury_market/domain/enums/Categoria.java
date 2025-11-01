package mercury_market.domain.enums;

public enum Categoria {
    BEBIDAS("Bebidas"),
    ELETRONICOS("Eletrônicos"),
    ROUPAS("Roupas"),
    CALCADOS("Calçados"),
    ALIMENTOS("Alimentos"),
    BRINQUEDOS("Brinquedos"),
    LIVROS("Livros"),
    MOVEIS("Móveis"),
    ESPORTES("Esportes"),
    BELEZA("Beleza e Cuidados Pessoais"),
    SAUDE("Saúde"),
    INFORMATICA("Informática"),
    FERRAMENTAS("Ferramentas"),
    DECORACAO("Decoração"),
    PET("Produtos para Pets"),
    AUTOPECAS("Autopeças"),
    INSTRUMENTOS_MUSICAIS("Instrumentos Musicais"),
    VIAGENS("Viagens e Turismo"),
    GAMES("Games"),
    FILMES_SERIES("Filmes e Séries");

    private final String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }


}
