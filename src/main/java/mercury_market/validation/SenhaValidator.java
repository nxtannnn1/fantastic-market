package mercury_market.validation;

import mercury_market.exceptions.SenhaInvalidaException;
import org.springframework.stereotype.Component;

@Component
public class SenhaValidator implements Validator<String> {

    @Override
    public String validar(String senha) {

        if (senha == null) throw new SenhaInvalidaException("Senha não pode ser nula!");

        if (senha.length() < 6 || senha.length() > 20)
            throw new SenhaInvalidaException("Senha não pode ter menos de 6 ou mais de 20 caracteres!");
        return senha;
    }
}
