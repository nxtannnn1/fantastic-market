package mercury_market.application.service;

import mercury_market.api.dto.request.CadastroRequest;
import mercury_market.api.dto.request.LoginRequest;
import mercury_market.api.dto.response.CadastroResponse;
import mercury_market.api.dto.response.LoginResponse;
import mercury_market.api.dto.response.TokenRecuperacaoResponse;
import mercury_market.api.mapper.CadastroMapper;
import mercury_market.api.mapper.LoginMapper;
import mercury_market.api.mapper.UsuarioMapper;
import mercury_market.domain.enums.TipoUsuario;
import mercury_market.domain.model.Usuario;
import mercury_market.exceptions.EmailJaCadastradoException;
import mercury_market.exceptions.EmailNaoEncontradoException;
import mercury_market.exceptions.SenhaInvalidaException;
import mercury_market.exceptions.UsuarioNaoEncontradoException;
import mercury_market.infrastructure.repository.UsuarioRepository;
import mercury_market.util.RandomString;
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
    private final JwtService jwtService;
    private final RandomString randomString;
    private final UsuarioMapper usuarioMapper;

    public AuthService(UsuarioRepository usuarioRepository,
                       CadastroMapper cadastroMapper,
                       LoginMapper loginMapper,
                       PasswordEncoder passwordEncoder,
                       SenhaValidator senhaValidator,
                       JwtService jwtService,
                       RandomString randomString,
                       UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.cadastroMapper = cadastroMapper;
        this.loginMapper = loginMapper;
        this.passwordEncoder = passwordEncoder;
        this.senhaValidator = senhaValidator;
        this.jwtService = jwtService;
        this.randomString = randomString;
        this.usuarioMapper = usuarioMapper;
    }

    public LoginResponse autenticarLogin(LoginRequest loginRequest) {
        var usuario = usuarioRepository.findByEmail(loginRequest.email()).orElseThrow(() -> new EmailNaoEncontradoException("Credenciais inválidas!"));
        if (!passwordEncoder.matches(loginRequest.senha(), usuario.getSenha()))
            throw new SenhaInvalidaException("Credenciais inválidas!");

        String token = jwtService.gerarToken(usuario.getEmail());
        return loginMapper.toDTO(usuario, token);
    }

    public CadastroResponse autenticarCadastro(CadastroRequest cadastroRequest) {
        if (usuarioRepository.existsByEmail(cadastroRequest.email()))
            throw new EmailJaCadastradoException("Credenciais inválidas!");

        var usuario = new Usuario();

        usuario.setNome(cadastroRequest.nome());
        usuario.setEmail(cadastroRequest.email());

        senhaValidator.validar(cadastroRequest.senha());

        usuario.setSenha(passwordEncoder.encode(cadastroRequest.senha()));
        usuario.setTipoUsuario(TipoUsuario.CLIENTE);

        usuarioRepository.save(usuario);
        return cadastroMapper.toDTO(usuario);
    }

    public Usuario obterUsuarioAutenticado() {
        String email = jwtService.obterEmailUsuarioAutenticado();
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new UsuarioNaoEncontradoException("Teste"));
    }

   /* public TokenRecuperacaoResponse esqueciMinhaSenha(String email, String segredo, String novaSenha) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new EmailNaoEncontradoException("Credenciais inválidas!"));

        String segredoGerado = randomString.nextString();

        if (!segredo.equals(segredoGerado)) {
            throw new RuntimeException("Token inválido");
        }

        usuario.setSenha(novaSenha);
        usuarioRepository.save(usuario);
        return usuarioMapper.toEsqueciSenhaDTO(usuario);
    }*/


}

