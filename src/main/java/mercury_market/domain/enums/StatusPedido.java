package mercury_market.domain.enums;

import lombok.Getter;

@Getter
public enum StatusPedido {

    PENDENTE("Pendente"),
    PAGO("Pago"),
    ENVIADO("Enviado"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

}

