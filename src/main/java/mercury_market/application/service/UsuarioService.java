package mercury_market.application.service;

import jakarta.transaction.Transactional;
import mercury_market.api.dto.response.UsuarioResponse;
import mercury_market.api.mapper.UsuarioMapper;
import mercury_market.exceptions.UsuarioNaoEncontradoException;
import mercury_market.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioMapper usuarioMapper, UsuarioRepository usuarioRepository) {
        this.usuarioMapper = usuarioMapper;
        this.usuarioRepository = usuarioRepository;
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

}
