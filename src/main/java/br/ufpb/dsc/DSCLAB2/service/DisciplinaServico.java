package br.ufpb.dsc.DSCLAB2.service;


import br.ufpb.dsc.DSCLAB2.dao.DisciplinaDAO;
import br.ufpb.dsc.DSCLAB2.dto.DisciplinaDTO;
import br.ufpb.dsc.DSCLAB2.entity.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class DisciplinaServico {
    @Autowired
    private DisciplinaDAO repositorioDeDisciplinas;

    /*@Autowired
    private ComentarioDAO repositorioDeComentarios;*/


    public DisciplinaServico(DisciplinaDAO repositorioDeDisciplinas/*, ComentarioDAO repositorioDeComentarios*/) {
        super();
        this.repositorioDeDisciplinas = repositorioDeDisciplinas;
        /*this.repositorioDeComentarios = repositorioDeComentarios;*/
        // TODO Auto-generated constructor stub
    }


    public Disciplina adicionaDisciplina(Disciplina disciplina) {
        Disciplina aDisciplina = new Disciplina(disciplina.getNome());
        Disciplina novaDisciplina = repositorioDeDisciplinas.save(aDisciplina);
        return novaDisciplina;
    }

    /*public Comentario adicionaComentario(long id, ComentarioDTO comentario) {
        if (repositorioDeDisciplinas.findById(id).isPresent()) {
            Comentario umComentario = new Comentario(repositorioDeDisciplinas.findById(id).get(), comentario.getTexto());
            repositorioDeComentarios.save(umComentario);
            return umComentario;
        }

        throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Disciplina nao existe.");
    }*/

    public List<Disciplina> getDisciplinas(){
        return repositorioDeDisciplinas.findAll();
    }

    public Disciplina getDisciplinaID(long id) {
        if (repositorioDeDisciplinas.findById(id).isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return repositorioDeDisciplinas.findById(id).get();
    }

    public DisciplinaDTO adicionaLikeDisciplina(long id, DisciplinaDTO disciplina) {

       /* Disciplina disciplina = getDisciplinaID(id);*/
        disciplina.setCurtida();
        /*repositorioDeDisciplinas.save(disciplina);*/
        return disciplina;
    }

/*
    public Disciplina adicionaNotaDisciplina(long id, double novaNota) {
        Disciplina disciplina = getDisciplinaID(id);
        disciplina.setNota(novaNota);
        repositorioDeDisciplinas.save(disciplina);
        return disciplina;

    }
*/



    /*public List<Disciplina> ranquearDisciplinasNota(){
        List<Disciplina> disciplinas = repositorioDeDisciplinas.findAll();
        Collections.sort(disciplinas, Collections.reverseOrder(new DisciplinaComparatorNota()));
        return disciplinas;

    }

    public List<Disciplina> ranquearDisciplinasCurtida(){
        List<Disciplina> disciplinas = repositorioDeDisciplinas.findAll();
        Collections.sort(disciplinas, Collections.reverseOrder(new DisciplinaComparatorCurtida()));
        return disciplinas;

    }*/








}

