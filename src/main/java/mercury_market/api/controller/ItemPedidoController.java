package mercury_market.api.controller;

import jakarta.validation.Valid;
import mercury_market.api.dto.request.ItemPedidoRequest;
import mercury_market.api.dto.response.ItemPedidoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mercury_market.application.service.ItemPedidoService;

@RestController
@RequestMapping("/item-pedido")
@CrossOrigin(origins = "[*]")
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    public ItemPedidoController(ItemPedidoService itemPedidoService){
        this.itemPedidoService=itemPedidoService;
    }

    @PostMapping
    public ResponseEntity<ItemPedidoResponse> criarItemPedido(@RequestBody @Valid ItemPedidoRequest itemPedidoRequest){
        return ResponseEntity.status(HttpStatus.OK).body(itemPedidoService.criarItemPedido(itemPedidoRequest));
    }
}
