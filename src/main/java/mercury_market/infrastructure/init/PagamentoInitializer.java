package mercury_market.infrastructure.init;

import mercury_market.infrastructure.presets.PagamentoPreset;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class PagamentoInitializer implements CommandLineRunner {
    private final PagamentoPreset pagamentoPreset;

    public PagamentoInitializer (PagamentoPreset pagamentoPreset){
        this.pagamentoPreset=pagamentoPreset;
    }

    public void run(String... args){
        pagamentoPreset.criarPagamentosPadrao();
    }

}
