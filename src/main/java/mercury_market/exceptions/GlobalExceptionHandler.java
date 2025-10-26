package mercury_market.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Map<String, Object>> handleAppExceptions(AppException ex) {
        HttpStatus status;

        if (ex instanceof EmailNaoEncontradoException
                || ex instanceof UsuarioNaoEncontradoException
                || ex instanceof ProdutoNaoEncontradoException) {
            status = HttpStatus.NOT_FOUND;

        } else if (ex instanceof EmailJaCadastradoException) {
            status = HttpStatus.CONFLICT;

        } else if (ex instanceof SenhaInvalidaException
                || ex instanceof SenhaIncorretaException) {
            status = HttpStatus.BAD_REQUEST;

        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        Map<String, Object> errorResponse = Map.of(
                "status", status.value(),
                "erro", ex.getClass().getSimpleName().replaceAll("([A-Z])", " $1").trim(),
                "detalhes", ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, status);
    }
}
