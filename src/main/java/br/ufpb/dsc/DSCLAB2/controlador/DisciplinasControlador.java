package br.ufpb.dsc.DSCLAB2.controlador;

import br.ufpb.dsc.DSCLAB2.DTOs.ComentarioDTO;
import br.ufpb.dsc.DSCLAB2.entidades.Comentario;
import br.ufpb.dsc.DSCLAB2.entidades.Disciplina;
import br.ufpb.dsc.DSCLAB2.servicos.ComentarioServico;
import br.ufpb.dsc.DSCLAB2.servicos.DisciplinaServico;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import javax.validation.Valid;


@RestController
@RequestMapping("/v1/api/disciplinas")
public class DisciplinasControlador {

    private final DisciplinaServico disciplinaServico;
    private final ComentarioServico comentarioServico;

    @Autowired
    public DisciplinasControlador(DisciplinaServico disciplinaServico, ComentarioServico comentarioServico) {
        this.disciplinaServico = disciplinaServico;
        this.comentarioServico = comentarioServico;
    }
    // Retorna um JSON (com campos id, nome) com todas as disciplinas inseridas no sistema
    @GetMapping
    public ResponseEntity<List<Disciplina>> getDisciplinas() {
        return new ResponseEntity<>(disciplinaServico.getDisciplinas(), HttpStatus.OK);
    }
    //Retorna um JSON que representa a disciplina completa (id, nome, nota, likes e comentarios) cujo identificador único é id e código
    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> getDisciplina(@Valid @PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(disciplinaServico.getDisciplina(id), HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Retorna todas as disciplinas inseridas no sistema ordenadas pela nota (da maior para a menor)
    @GetMapping("/ranking/notas")
    public ResponseEntity<List<Disciplina>> getRankingByNotas() {
        return new ResponseEntity<>(disciplinaServico.getRankingByNotas(), HttpStatus.OK);
    }
    //Incrementa em um o número de curtidas da disciplina cujo identificador é id.
    @GetMapping("/ranking/curtidas")
    public ResponseEntity<List<Disciplina>> getRankingByCurtidas() {
        return new ResponseEntity<>(disciplinaServico.getRankingByCurtidas(), HttpStatus.OK);
    }
    //Retorna todas as disciplinas inseridas no sistema ordenadas pelo número de curtidas (da que tem mais curtidas para a que tem menos curtidas)
    @PostMapping("/curtidas/{id}")
    public ResponseEntity<Disciplina> addCurtida(@Valid @PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(disciplinaServico.addCurtida(id), HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Insere um novo comentário na disciplina de identificador id
    @PostMapping("/comentarios/{id}")
    public ResponseEntity<Disciplina> addComentario(@Valid @PathVariable("id") Long id, @Valid @RequestBody ComentarioDTO comentarioDTO) {
        try {
            Disciplina disciplina = disciplinaServico.getDisciplina(id);
            Comentario comentario = new Comentario(comentarioDTO.getComentario());
            comentario.setDisciplina(disciplina);
            comentarioServico.addComentario(comentario);

            return new ResponseEntity<>(disciplina, HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Atualiza a nota da disciplina de identificador id no sistema
    @PatchMapping("/nota/{id}")
    public ResponseEntity<Disciplina> updateNota(@Valid @PathVariable("id") Long id, @Valid @RequestBody ObjectNode json) {
        try {
            double newNota = json.get("nota").asDouble();
            return new ResponseEntity<>(disciplinaServico.updateDisciplinaNota(id, newNota), HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
