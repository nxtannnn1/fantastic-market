package mercury_market.exceptions;

public abstract class AppException extends RuntimeException {

    public AppException(String mensagem) {
        super(mensagem);
    }

}
