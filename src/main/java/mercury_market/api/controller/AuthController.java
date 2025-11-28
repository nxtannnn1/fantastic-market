package mercury_market.api.controller;

import jakarta.validation.Valid;
import mercury_market.api.dto.request.CadastroRequest;
import mercury_market.api.dto.request.LoginRequest;
import mercury_market.api.dto.response.CadastroResponse;
import mercury_market.api.dto.response.LoginResponse;
import mercury_market.application.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<CadastroResponse> cadastrar(@RequestBody @Valid CadastroRequest dto) {
        var usuario = authService.autenticarCadastro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest dto) {
        var usuario = authService.autenticarLogin(dto);
        return ResponseEntity.ok(usuario);
    }

}

