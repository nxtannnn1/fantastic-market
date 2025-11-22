package mercury_market.infrastructure.presets;

import jakarta.transaction.Transactional;
import mercury_market.domain.enums.TipoUsuario;
import mercury_market.domain.model.Usuario;
import mercury_market.infrastructure.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPreset {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioPreset(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
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
}
