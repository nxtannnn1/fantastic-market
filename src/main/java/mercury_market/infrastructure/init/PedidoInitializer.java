package mercury_market.infrastructure.init;

import mercury_market.infrastructure.presets.PedidoPreset;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(3)
@Component
@DependsOn("usuarioInitializer")
public class PedidoInitializer implements CommandLineRunner {

    private final PedidoPreset pedidoPreset;

    public PedidoInitializer(PedidoPreset pedidoPreset){
        this.pedidoPreset=pedidoPreset;
    }

    @Override
    public void run(String... args){
        pedidoPreset.criarPedidosPadrao();
    }

}
