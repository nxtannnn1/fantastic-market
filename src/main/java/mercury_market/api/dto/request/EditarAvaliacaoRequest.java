package mercury_market.api.dto.request;

public record EditarAvaliacaoRequest(
        Integer nota,
        String comentario
) {
}
