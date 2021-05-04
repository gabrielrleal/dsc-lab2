package io.github.gabrielrleal.lab2dsc.controller;

import io.github.gabrielrleal.lab2dsc.dto.ComentarioDTO;
import io.github.gabrielrleal.lab2dsc.dto.DisciplinaDTO;
import io.github.gabrielrleal.lab2dsc.dto.DisciplinaLikeDTO;
import io.github.gabrielrleal.lab2dsc.model.Comentario;
import io.github.gabrielrleal.lab2dsc.model.Disciplina;
import io.github.gabrielrleal.lab2dsc.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaServico;

    public DisciplinaController(DisciplinaService disciplinaServico) {
        super();
        this.disciplinaServico = disciplinaServico;
        // TODO Auto-generated constructor stub
    }

    @PostMapping("/disciplinas")
    public ResponseEntity<Disciplina> adicionaDisciplina(@RequestBody DisciplinaDTO disciplina){
        return new ResponseEntity<Disciplina>(disciplinaServico.adicionaDisciplina(disciplina), HttpStatus.CREATED);
    }

    @PostMapping("/disciplinas/comentario/{id}")
    public ResponseEntity<Comentario> adicionaComentario(@PathVariable long id, @RequestBody ComentarioDTO comentario){
        try {
            return new ResponseEntity<Comentario>(disciplinaServico.adicionaComentario(id, comentario), HttpStatus.CREATED);
        } catch (HttpClientErrorException hce) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/disciplinas")
    public ResponseEntity<List<Disciplina>> getDisciplinas(){
        return new ResponseEntity<List<Disciplina>>(disciplinaServico.getDisciplinas(), HttpStatus.OK);
    }

    @GetMapping("/disciplinas/{id}")
    public ResponseEntity<Disciplina> getDisciplinasId(@PathVariable long id){
        try {
            return new ResponseEntity<Disciplina>(disciplinaServico.getDisciplinasID(id), HttpStatus.OK);
        } catch (HttpClientErrorException hce) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/disciplinas/likes/id")
    public ResponseEntity<DisciplinaLikeDTO> atualizaLikes(@PathVariable long id, @RequestBody DisciplinaLikeDTO disciplina, int like){
        try {
            return new ResponseEntity<DisciplinaLikeDTO>(disciplinaServico.adicionaLikeDisciplina(id, disciplina, like), HttpStatus.OK);
        } catch (HttpClientErrorException hce) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
