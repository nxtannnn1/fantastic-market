package mercury_market.infrastructure.presets;

import jakarta.transaction.Transactional;
import mercury_market.infrastructure.repository.PagamentoRepository;
import mercury_market.infrastructure.repository.PedidoRepository;
import mercury_market.infrastructure.repository.UsuarioRepository;

public class PagamentoPreset {

    private final PagamentoRepository pagamentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PedidoRepository pedidoRepository;

    public PagamentoPreset(PagamentoRepository pagamentoRepository,
                           UsuarioRepository usuarioRepository,
                           PedidoRepository pedidoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }

  /*  @Transactional
    public void criarProdutosPadrao() {

        var cliente = usuarioRepository.findByEmail("cliente@mm.com");
        var pedido = pedidoRepository.findById();
    }*/
}
