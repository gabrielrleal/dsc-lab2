package br.ufpb.dsc.DSCLAB2.controlador;

import br.ufpb.dsc.DSCLAB2.DTOs.UsuarioLoginDTO;
import br.ufpb.dsc.DSCLAB2.JWT.LoginResponse;
import br.ufpb.dsc.DSCLAB2.servicos.JWTServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/auth")
public class LoginControlador {

    @Autowired
    private JWTServico jwtServico;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> autentica(@RequestBody UsuarioLoginDTO usuario) {
        return new ResponseEntity<LoginResponse>(jwtServico.autentica(usuario), HttpStatus.OK);
    }

}