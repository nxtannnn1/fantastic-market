package mercury_market.api.controller;

import jakarta.validation.Valid;
import mercury_market.api.dto.request.LoginRequest;
import mercury_market.api.dto.request.UsuarioRequest;
import mercury_market.api.dto.response.LoginResponse;
import mercury_market.api.dto.response.UsuarioResponse;
import mercury_market.application.service.LoginService;
import mercury_market.application.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final LoginService loginService;

    public AuthController(UsuarioService usuarioService, LoginService loginService) {
        this.usuarioService = usuarioService;
        this.loginService = loginService;
    }

    // CADASTRO
    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody @Valid UsuarioRequest dto) {
        var usuario = usuarioService.cadastrarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        var loginResponse = loginService.autenticarLogin(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}

