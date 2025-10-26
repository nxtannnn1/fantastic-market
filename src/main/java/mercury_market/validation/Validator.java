package mercury_market.validation;

public interface Validator<T> {

    T validar(T entity);
}
