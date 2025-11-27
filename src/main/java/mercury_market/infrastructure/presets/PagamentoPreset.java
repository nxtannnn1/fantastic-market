package mercury_market.infrastructure.presets;

import jakarta.transaction.Transactional;
import mercury_market.domain.enums.TipoPagamento;
import mercury_market.domain.model.Pagamento;
import mercury_market.exceptions.UsuarioNaoEncontradoException;
import mercury_market.infrastructure.repository.PagamentoRepository;
import mercury_market.infrastructure.repository.PedidoRepository;
import mercury_market.infrastructure.repository.UsuarioRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Order(4)
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

    @Transactional
    public void criarPagamentosPadrao() {
        Pagamento pagamento = new Pagamento();
        var cliente = usuarioRepository.findByEmail("cliente@mm.com").orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado!"));
        var pedido = pedidoRepository.findById(1L).orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));
        pagamento.setUsuario(cliente);
        pagamento.setPedido(pedido);
        pagamento.setTipoPagamento(TipoPagamento.PIX);
        pagamento.setDataPagamento(LocalDateTime.now());
        pagamentoRepository.save(pagamento);
    }
}
