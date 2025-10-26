package mercury_market.application.service;

import jakarta.annotation.PostConstruct;
import mercury_market.api.dto.request.CadastroRequest;
import mercury_market.api.dto.request.LoginRequest;
import mercury_market.api.dto.response.CadastroResponse;
import mercury_market.api.dto.response.LoginResponse;
import mercury_market.api.mapper.CadastroMapper;
import mercury_market.api.mapper.LoginMapper;
import mercury_market.domain.enums.TipoUsuario;
import mercury_market.domain.model.Usuario;
import mercury_market.exceptions.EmailJaCadastradoException;
import mercury_market.exceptions.EmailNaoEncontradoException;
import mercury_market.exceptions.SenhaInvalidaException;
import mercury_market.infrastructure.repository.UsuarioRepository;
import mercury_market.validation.SenhaValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final LoginMapper loginMapper;
    private final CadastroMapper cadastroMapper;
    private final PasswordEncoder passwordEncoder;
    private final SenhaValidator senhaValidator;

    public AuthService(UsuarioRepository usuarioRepository,
                       CadastroMapper cadastroMapper,
                       LoginMapper loginMapper,
                       PasswordEncoder passwordEncoder,
                       SenhaValidator senhaValidator) {
        this.usuarioRepository = usuarioRepository;
        this.loginMapper = loginMapper;
        this.cadastroMapper = cadastroMapper;
        this.passwordEncoder = passwordEncoder;
        this.senhaValidator = senhaValidator;
    }

    @PostConstruct
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

    public LoginResponse autenticarLogin(LoginRequest loginRequest) {
        var usuario = usuarioRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new EmailNaoEncontradoException("Não foi encontrado um usuário com esse email"));
        if (!passwordEncoder.matches(loginRequest.getSenha(), usuario.getSenha()))
            throw new SenhaInvalidaException("Senha incorreta");
        return loginMapper.toDTO(usuario);
    }

    public CadastroResponse autenticarCadastro(CadastroRequest cadastroRequest) {
        if (usuarioRepository.existsByEmail(cadastroRequest.getEmail()))
            throw new EmailJaCadastradoException("Já existe um usuário com esse email");

        var usuario = new Usuario();

        usuario.setNome(cadastroRequest.getNome());
        usuario.setEmail(cadastroRequest.getEmail());

        senhaValidator.validar(cadastroRequest.getSenha());

        usuario.setSenha(passwordEncoder.encode(cadastroRequest.getSenha()));
        usuario.setTipoUsuario(TipoUsuario.CLIENTE);

        usuarioRepository.save(usuario);
        return cadastroMapper.toDTO(usuario);
    }
}
