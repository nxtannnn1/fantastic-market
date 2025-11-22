package mercury_market.infrastructure.init;

import mercury_market.infrastructure.presets.UsuarioPreset;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInitializer implements CommandLineRunner {

    private final UsuarioPreset usuarioPreset;

    public UsuarioInitializer(UsuarioPreset usuarioPreset) {
        this.usuarioPreset = usuarioPreset;
    }

    @Override
    public void run(String... varargs) {
        usuarioPreset.criarUsuariosPadrao();
    }
}
