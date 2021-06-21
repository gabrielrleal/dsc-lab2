package br.ufpb.dsc.DSCLAB2.servicos;

import br.ufpb.dsc.DSCLAB2.DAOs.UsuarioDAO;
import br.ufpb.dsc.DSCLAB2.DTOs.UsuarioDTO;
import br.ufpb.dsc.DSCLAB2.entidades.Usuario;
import br.ufpb.dsc.DSCLAB2.excecoes.UsuarioInvalidoException;
import br.ufpb.dsc.DSCLAB2.excecoes.UsuarioExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.util.Optional;


@Service
public class UsuarioServico {

    @Autowired
    private UsuarioDAO usuarioRepository;
    @Autowired
    private JWTServico jwtService;

    public UsuarioDTO addUsuario(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
            throw new UsuarioExisteException();
        if (!usuario.isValid())
            throw new UsuarioInvalidoException();
        usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario);
    }

    public Usuario getUsuario(String email) {
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(email);
        if(optUsuario.isEmpty())
            throw new IllegalArgumentException();
        return optUsuario.get();
    }
    private boolean usuarioTemPermissao(String authorizationHeader, String email) throws ServletException {
        String subject = jwtService.getSujeitoDoToken(authorizationHeader);
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(subject);
        return optUsuario.isPresent() && optUsuario.get().getEmail().equals(email);
    }

    public UsuarioDTO deletaUsuario(String cabecalhoDeAutorizacao) {
        Optional<String> usuarioId = jwtService.recuperaUsuario(cabecalhoDeAutorizacao);

        Usuario usuario = validaUsuario(usuarioId);
        usuarioRepository.delete(usuario);
        return new UsuarioDTO(usuario);
    }

    private Usuario validaUsuario(Optional<String> id) {
        if(id.isEmpty()) throw new UsuarioInvalidoException();
        Optional<Usuario> usuario = usuarioRepository.findByEmail(id.get());
        if (usuario.isEmpty()) throw new UsuarioInvalidoException();
        return usuario.get();
    }
}
