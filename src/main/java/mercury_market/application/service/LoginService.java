package mercury_market.application.service;

import jakarta.transaction.Transactional;
import mercury_market.api.dto.request.LoginRequest;
import mercury_market.api.dto.response.LoginResponse;
import mercury_market.api.mapper.LoginMapper;
import mercury_market.domain.model.Usuario;
import mercury_market.domain.enums.TipoUsuario;
import mercury_market.infrastructure.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UsuarioRepository usuarioRepository;
    private final LoginMapper loginMapper;
    private final PasswordEncoder passwordEncoder;

    public LoginService(UsuarioRepository usuarioRepository,
                        LoginMapper loginMapper,
                        PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.loginMapper = loginMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void criarUsuariosPadrao() {
        var adminEmail = "admin@mm.com";
        var clienteEmail = "cliente@mm.com";
        var vendedorEmail = "vendedor@mm.com";

        if (usuarioRepository.findByEmail(adminEmail).isEmpty()) {
            var admin = new Usuario();
            admin.setNome("Admin");
            admin.setEmail(adminEmail);
            admin.setSenha(passwordEncoder.encode("Admin123!"));
            admin.setTipoUsuario(TipoUsuario.ADM);
            usuarioRepository.save(admin);
        }

        if (usuarioRepository.findByEmail(clienteEmail).isEmpty()) {
            var cliente = new Usuario();
            cliente.setNome("Cliente");
            cliente.setEmail(clienteEmail);
            cliente.setSenha(passwordEncoder.encode("Cliente123!"));
            cliente.setTipoUsuario(TipoUsuario.CLIENTE);
            usuarioRepository.save(cliente);
        }

        if (usuarioRepository.findByEmail(vendedorEmail).isEmpty()) {
            var vendedor = new Usuario();
            vendedor.setNome("Vendedor");
            vendedor.setEmail(vendedorEmail);
            vendedor.setSenha(passwordEncoder.encode("Vendedor123!"));
            vendedor.setTipoUsuario(TipoUsuario.VENDEDOR);
            usuarioRepository.save(vendedor);
        }
    }


    @Transactional
    public LoginResponse autenticarLogin(LoginRequest loginRequest) {
        var usuario = usuarioRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new RuntimeException("Não foi encontrado um usuário com esse email"));
        if (!passwordEncoder.matches(loginRequest.getSenha(), usuario.getSenha()))
            throw new RuntimeException("Senha inválida");
        return loginMapper.toDTO(usuario);
    }
}
