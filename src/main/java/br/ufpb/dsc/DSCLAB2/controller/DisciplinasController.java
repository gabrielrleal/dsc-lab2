package br.ufpb.dsc.DSCLAB2.controller;

import br.ufpb.dsc.DSCLAB2.dto.DisciplinaDTO;
import br.ufpb.dsc.DSCLAB2.entity.Disciplina;
import br.ufpb.dsc.DSCLAB2.service.DisciplinaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
public class DisciplinasController {

    @Autowired
    private DisciplinaServico disciplinaServico;

    public DisciplinasController(DisciplinaServico disciplinaServico) {
        super();
        this.disciplinaServico = disciplinaServico;
        // TODO Auto-generated constructor stub
    }

    @PostMapping("/disciplinas")
    public ResponseEntity<Disciplina> adicionaDisciplina(@RequestBody Disciplina disciplina){
        return new ResponseEntity<Disciplina>(disciplinaServico.adicionaDisciplina(disciplina), HttpStatus.CREATED);
    }

   /* @PostMapping("/disciplinas/comentario/{id}")
    public ResponseEntity<Comentario> adicionaComentario(@PathVariable long id, @RequestBody ComentarioDTO comentario){
        try {
            return new ResponseEntity<Comentario>(disciplinaServico.adicionaComentario(id, comentario), HttpStatus.CREATED);
        } catch (HttpClientErrorException hce) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }*/

    @GetMapping("/disciplinas")
    public ResponseEntity<List<Disciplina>> getDisciplinas(){
        return new ResponseEntity<List<Disciplina>>(disciplinaServico.getDisciplinas(), HttpStatus.OK);
    }

    @GetMapping("/disciplinas/{id}")
    public ResponseEntity<Disciplina> getDisciplinasId(@PathVariable long id){
        try {
            return new ResponseEntity<Disciplina>(disciplinaServico.getDisciplinaID(id), HttpStatus.OK);
        } catch (HttpClientErrorException hce) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/disciplinas/likes/id")
    public ResponseEntity<DisciplinaDTO> atualizaLikes(@PathVariable long id,@RequestBody DisciplinaDTO disciplina){
        try {
            return new ResponseEntity<DisciplinaDTO>(disciplinaServico.adicionaLikeDisciplina(id, disciplina), HttpStatus.OK);
        } catch (HttpClientErrorException hce) {
            return new ResponseEntity<DisciplinaDTO>(HttpStatus.NOT_FOUND);
        }
    }

 /*   @PatchMapping("/disciplinas/notas/id")
    public ResponseEntity<Disciplina> atualizaNota(@PathVariable long id, @RequestBody double nota){
        try {
            return new ResponseEntity<Disciplina>(disciplinaServico.adicionaNotaDisciplina(id, nota), HttpStatus.OK);
        } catch (HttpClientErrorException hce) {
            return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/disciplinas/ranking/notas")
    public ResponseEntity<List<Disciplina>> ranquearDisciplinasNota(){
        return new ResponseEntity<List<Disciplina>>(disciplinaServico.ranquearDisciplinasNota(), HttpStatus.OK);
    }

    @GetMapping("/disciplinas/ranking/likes")
    public ResponseEntity<List<Disciplina>> ranquearDisciplinasCurtida(){
        return new ResponseEntity<List<Disciplina>>(disciplinaServico.ranquearDisciplinasCurtida(), HttpStatus.OK);
    }
*/






}
