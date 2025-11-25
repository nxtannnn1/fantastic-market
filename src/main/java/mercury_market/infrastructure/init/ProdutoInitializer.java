package mercury_market.infrastructure.init;

import mercury_market.infrastructure.presets.ProdutoPreset;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProdutoInitializer implements CommandLineRunner {

    private final ProdutoPreset produtoPreset;

    public ProdutoInitializer(ProdutoPreset produtoPreset) {
        this.produtoPreset = produtoPreset;
    }

    @Override
    public void run(String... varargs) {
        produtoPreset.criarProdutosPadrao();
    }
}
