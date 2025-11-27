package mercury_market.application.service;

import mercury_market.api.dto.request.PagamentoRequest;
import mercury_market.api.dto.response.PagamentoResponse;
import mercury_market.api.mapper.PagamentoMapper;
import mercury_market.domain.enums.StatusPedido;
import mercury_market.domain.enums.TipoPagamento;
import mercury_market.domain.model.Pagamento;
import mercury_market.exceptions.PedidoNaoEncontradoException;
import mercury_market.infrastructure.repository.PagamentoRepository;
import mercury_market.infrastructure.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper pagamentoMapper;
    private final AuthService authService;
    private final PedidoRepository pedidoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository,
                            PagamentoMapper pagamentoMapper,
                            AuthService authService,
                            PedidoRepository pedidoRepository
    ) {
        this.pagamentoRepository = pagamentoRepository;
        this.authService = authService;
        this.pedidoRepository = pedidoRepository;
        this.pagamentoMapper = pagamentoMapper;
    }

    public PagamentoResponse processsarPagamento(PagamentoRequest pagamentoRequest) {

        var cliente = authService.obterUsuarioAutenticado();
        var pedido = pedidoRepository.findById(pagamentoRequest.pedidoId()).orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado"));

        Pagamento pagamento = new Pagamento();
        pagamento.setCliente(cliente);
        pedido.setStatusPedido(StatusPedido.PAGO);
        pagamento.setPedido(pedido);
        pagamento.setTipoPagamento(TipoPagamento.valueOf(pagamentoRequest.tipoPagamento()));
        pagamento.setDataPagamento(LocalDateTime.now());
        pedido.setPagamento(pagamento);

        pagamentoRepository.save(pagamento);
        pedidoRepository.save(pedido);

        return pagamentoMapper.toDTO(pagamento);
    }


}
