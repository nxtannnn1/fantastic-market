package mercury_market.infrastructure.init;

import mercury_market.infrastructure.presets.ItemPedidoPreset;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class ItemPedidoInitializer implements CommandLineRunner {
    private final ItemPedidoPreset itemPedidoPreset;

    public ItemPedidoInitializer(ItemPedidoPreset itemPedidoPreset) {
        this.itemPedidoPreset = itemPedidoPreset;
    }

    @Override
    public void run(String... args) {
        itemPedidoPreset.criarItemPedidosPadrao();
    }

}
