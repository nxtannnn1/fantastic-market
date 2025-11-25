package mercury_market.domain.enums;

import lombok.Getter;

@Getter
public enum TipoPagamento {
    CARTAO_CREDITO("Cartão de Crédito"),
    PIX("Pix"),
    BOLETO("Boleto Bancário");

    private final String descricao;

    TipoPagamento(String descricao){
        this.descricao=descricao;
    }

}
