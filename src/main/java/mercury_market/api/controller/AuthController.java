package mercury_market.api.controller;

import jakarta.validation.Valid;
import mercury_market.api.dto.request.LoginRequest;
import mercury_market.api.dto.request.UsuarioRequest;
import mercury_market.api.dto.response.LoginResponse;
import mercury_market.api.dto.response.UsuarioResponse;
import mercury_market.application.service.AuthService;
import mercury_market.application.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioService usuarioService;
    private final AuthService authService;

    public AuthController(UsuarioService usuarioService, AuthService authService) {
        this.usuarioService = usuarioService;
        this.authService = authService;
    }

    // CADASTRO
    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody @Valid UsuarioRequest dto) {
        var usuario = usuarioService.cadastrarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest dto) {
        var loginResponse = authService.autenticarLogin(dto);
        return ResponseEntity.ok(loginResponse);
    }
}

