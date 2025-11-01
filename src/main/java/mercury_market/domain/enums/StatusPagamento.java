package mercury_market.domain.enums;

public enum StatusPagamento {

    APROVADO("Aprovado"),
    EM_ANDAMENTO("Em Andamento"),
    PENDENTE("Pendente"),
    RECUSADO("Recusado");

    private final String descricao;

    StatusPagamento(String descricao){
        this.descricao=descricao;
    }
}
