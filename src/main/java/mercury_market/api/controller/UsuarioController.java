package mercury_market.api.controller;

import jakarta.validation.Valid;
import mercury_market.api.dto.request.AlterarSenhaUsuarioRequest;
import mercury_market.api.dto.response.AlterarSenhaUsuarioResponse;
import mercury_market.api.dto.response.UsuarioResponse;
import mercury_market.application.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarios());
    }

    @PatchMapping("alterar-senha/{id}")
    public ResponseEntity<AlterarSenhaUsuarioResponse> alterarSenha(@RequestBody @Valid AlterarSenhaUsuarioRequest alterarSenhaUsuarioRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.alterarSenha(alterarSenhaUsuarioRequest.email(), alterarSenhaUsuarioRequest.senhaAntiga(), alterarSenhaUsuarioRequest.senhaNova()));
    }
}
