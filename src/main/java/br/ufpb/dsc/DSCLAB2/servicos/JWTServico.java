package br.ufpb.dsc.DSCLAB2.servicos;

import br.ufpb.dsc.DSCLAB2.DAOs.UsuarioDAO;
import br.ufpb.dsc.DSCLAB2.DTOs.UsuarioLoginDTO;
import br.ufpb.dsc.DSCLAB2.JWT.LoginResponse;
import br.ufpb.dsc.DSCLAB2.entidades.Usuario;
import br.ufpb.dsc.DSCLAB2.filtro.TokenFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class JWTServico {

    @Autowired
    private UsuarioDAO usuarioRepository;

    public LoginResponse autentica(UsuarioLoginDTO usuario) {
        String msgErro = "Email e/ou senha invalido(s). Login nao realizado";
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (optUsuario.isPresent() && usuario.getSenha().equals(optUsuario.get().getSenha()))
            return new LoginResponse(geraToken(usuario));

        return new LoginResponse(msgErro);

    }

    private String geraToken(UsuarioLoginDTO usuario) {
        String token = Jwts.builder().setSubject(usuario.getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000)).compact();
        return token;
    }

    public static final String TOKEN_KEY = "ja pode sair?";

    public Optional<String> recuperaUsuario(String cabecalhoAutorizacao) {
        if (cabecalhoAutorizacao == null ||
                !cabecalhoAutorizacao.startsWith("Bearer ")) {
            throw new SecurityException();
        }
        String token = cabecalhoAutorizacao.substring(TokenFilter.TOKEN_INDEX);

        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
        } catch (SignatureException e) {
            throw new SecurityException("Token invalido ou expirado!");
        }
        return Optional.of(subject);
    }

    public String getSujeitoDoToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new SecurityException("Token inexistente ou mal formatado!");
        }

        // Extraindo apenas o token do cabecalho.
        String token = authorizationHeader.substring(TokenFilter.TOKEN_INDEX);

        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
        } catch (SignatureException e) {
            throw new SecurityException("Token invalido ou expirado!");
        }
        return subject;
    }


}
