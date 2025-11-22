package mercury_market.api.dto.response;

public record LoginResponse(
        String email,
        String nome,
        String token) {
}
