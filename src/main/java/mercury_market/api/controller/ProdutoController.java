package mercury_market.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @GetMapping("/hello")
    public String hello(){
        return "Oi";
    }
}
