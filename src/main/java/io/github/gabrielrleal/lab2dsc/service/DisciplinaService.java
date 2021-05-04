package io.github.gabrielrleal.lab2dsc.service;

import java.util.List;

import io.github.gabrielrleal.lab2dsc.dao.ComentarioDAO;
import io.github.gabrielrleal.lab2dsc.dao.DisciplinaDAO;
import io.github.gabrielrleal.lab2dsc.dto.ComentarioDTO;
import io.github.gabrielrleal.lab2dsc.dto.DisciplinaDTO;
import io.github.gabrielrleal.lab2dsc.dto.DisciplinaLikeDTO;
import io.github.gabrielrleal.lab2dsc.dto.DisciplinaNotaDTO;
import io.github.gabrielrleal.lab2dsc.model.Comentario;
import io.github.gabrielrleal.lab2dsc.model.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;



@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaDAO repositorioDeDisciplinas;

    @Autowired
    private ComentarioDAO repositorioDeComentarios;

    public DisciplinaService(DisciplinaDAO repositorioDeDisciplinas, ComentarioDAO repositorioDeComentarios) {
        super();
        this.repositorioDeDisciplinas = repositorioDeDisciplinas;
        this.repositorioDeComentarios = repositorioDeComentarios;
        // TODO Auto-generated constructor stub
    }


    public Disciplina adicionaDisciplina(DisciplinaDTO disciplina) {
        Disciplina aDisciplina = new Disciplina(disciplina.getNome());
        Disciplina novaDisciplina = repositorioDeDisciplinas.save(aDisciplina);
        return novaDisciplina;
    }

    public Comentario adicionaComentario(long id, ComentarioDTO comentario) {
        if (repositorioDeDisciplinas.findById(id).isPresent()) {
            Comentario umComentario = new Comentario(repositorioDeDisciplinas.findById(id).get(), comentario.getTexto());
            repositorioDeComentarios.save(umComentario);
            return umComentario;
        }

        throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Disciplina nao existe.");
    }

    public List<Disciplina> getDisciplinas(){
        return repositorioDeDisciplinas.findAll();
    }

    public Disciplina getDisciplinasID(long id) {
        if (repositorioDeDisciplinas.findById(id).isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return repositorioDeDisciplinas.findById(id).get();
    }

    public DisciplinaLikeDTO adicionaLikeDisciplina(long id, DisciplinaLikeDTO disciplina, int like) {
        if(repositorioDeDisciplinas.findById(id).isPresent()) {
            disciplina.setLike(like);
            return disciplina;
        }
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Disciplina nao existe.");
    }

    public DisciplinaNotaDTO adicionaNotaDisciplina(long id, DisciplinaNotaDTO disciplina, double nota) {
        if(repositorioDeDisciplinas.findById(id).isPresent()) {

        }
        return disciplina;
    }








}
