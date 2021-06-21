package br.ufpb.dsc.DSCLAB2.controlador;

import br.ufpb.dsc.DSCLAB2.DTOs.UsuarioDTO;
import br.ufpb.dsc.DSCLAB2.entidades.Usuario;
import br.ufpb.dsc.DSCLAB2.excecoes.UsuarioInvalidoException;
import br.ufpb.dsc.DSCLAB2.excecoes.UsuarioExisteException;

import br.ufpb.dsc.DSCLAB2.servicos.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServico usuarioServico;

    @PostMapping
    public ResponseEntity<UsuarioDTO> addUsuario(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<UsuarioDTO>(usuarioServico.addUsuario(usuario), HttpStatus.CREATED);
        } catch (UsuarioExisteException uee) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (UsuarioInvalidoException uie) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<UsuarioDTO> deletaUsuario(@RequestHeader("Authorization") String token) {
        try {
            return new ResponseEntity<>(usuarioServico.deletaUsuario(token), HttpStatus.OK);
        } catch (UsuarioExisteException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (UsuarioInvalidoException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

}
