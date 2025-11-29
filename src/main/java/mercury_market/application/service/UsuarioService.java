package mercury_market.application.service;

import jakarta.transaction.Transactional;
import mercury_market.api.dto.request.UsuarioRequest;
import mercury_market.api.dto.response.AlterarEmailUsuarioResponse;
import mercury_market.api.dto.response.AlterarSenhaUsuarioResponse;
import mercury_market.api.dto.response.UsuarioResponse;
import mercury_market.api.mapper.UsuarioMapper;
import mercury_market.domain.model.Usuario;
import mercury_market.exceptions.EmailJaCadastradoException;
import mercury_market.exceptions.EmailNaoEncontradoException;
import mercury_market.exceptions.SenhaIncorretaException;
import mercury_market.exceptions.UsuarioNaoEncontradoException;
import mercury_market.infrastructure.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  UsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioMapper usuarioMapper, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioMapper = usuarioMapper;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioMapper.toDTO(usuarioRepository.findAll());
    }

    @Transactional
    public UsuarioResponse listarUsuarioPorId(Long id) {
        var usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário de id " + id + " não encontrado!"));
        return usuarioMapper.toDTO(usuario);
    }

    @Transactional
    public UsuarioResponse editarUsuarioPorEmail(String email, UsuarioRequest usuarioRequest) {

        var usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new UsuarioNaoEncontradoException("Credenciais do usuário inválidas"));

        usuario.setNome(usuarioRequest.nome());

        if (usuario.getEmail().equals(email)) {

            if (usuarioRepository.existsByEmail(email)) {
                throw new EmailJaCadastradoException("Email já Cadastrado");
            }
            usuario.setEmail(usuarioRequest.email());

        }

        usuario.setSenha(passwordEncoder.encode(usuarioRequest.senha()));
        usuario.setTipoUsuario(usuarioRequest.tipoUsuario());

        usuarioRepository.save(usuario);

        return usuarioMapper.toDTO(usuario);
    }

    @Transactional
    public AlterarEmailUsuarioResponse alterarEmail(String emailAntigo, String emailNovo) {

        Usuario usuario = usuarioRepository.findByEmail(emailAntigo).orElseThrow(() -> new EmailNaoEncontradoException("Credenciais inválidas!"));

        if (usuarioRepository.existsByEmail(emailNovo)) {
            throw new EmailJaCadastradoException("Email já Cadastrado");
        }

        usuario.setEmail(emailNovo);
        usuarioRepository.save(usuario);

        return usuarioMapper.toEmailDTO(usuario);

    }

    @Transactional
    public AlterarSenhaUsuarioResponse alterarSenha(String email, String senhaAntiga, String senhaNova) {

        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new EmailNaoEncontradoException("Credenciais inválidas!"));

        if (!passwordEncoder.matches(senhaAntiga, usuario.getSenha())) {
            throw new SenhaIncorretaException("Credenciais inválidas!");
        }

        usuario.setSenha(senhaNova);

        usuarioRepository.save(usuario);

        return usuarioMapper.toSenhaDTO(usuario);
    }


}
