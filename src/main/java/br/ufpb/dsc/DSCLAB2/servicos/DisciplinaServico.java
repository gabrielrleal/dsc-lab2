package br.ufpb.dsc.DSCLAB2.servicos;
import br.ufpb.dsc.DSCLAB2.DAOs.DisciplinaDAO;
import br.ufpb.dsc.DSCLAB2.DTOs.ComentarioDTO;
import br.ufpb.dsc.DSCLAB2.entidades.Comentario;
import br.ufpb.dsc.DSCLAB2.entidades.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaServico {

    private final DisciplinaDAO repositorioDeDisciplina;

    @Autowired
    public DisciplinaServico(DisciplinaDAO repositorioDeDisciplina) {
        this.repositorioDeDisciplina = repositorioDeDisciplina;
    }

    public void saveAllDisciplinas(List<Disciplina> disciplinas) {
        this.repositorioDeDisciplina.saveAll(disciplinas);
    }

    public List<Disciplina> getDisciplinas() {
        return this.repositorioDeDisciplina.findAll();
    }

    public Disciplina getDisciplina(Long id) throws HttpClientErrorException {
        Optional<Disciplina> disciplina = this.repositorioDeDisciplina.findById(id);

        return disciplina.orElseThrow(() ->
                new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public Disciplina addCurtida(Long id) throws HttpClientErrorException {
        Disciplina disciplina = getDisciplina(id);
        disciplina.setCurtidas(disciplina.getCurtidas() + 1);
        return this.repositorioDeDisciplina.save(disciplina);
    }

    public List<Disciplina> getRankingByNotas() {
        return this.repositorioDeDisciplina.findByOrderByNotaDesc();
    }

    public List<Disciplina> getRankingByCurtidas() {
        return this.repositorioDeDisciplina.findByOrderByCurtidasDesc();
    }

    private double calcularNota(double olderNota, double newNota) {
        if (olderNota == 0.0) return newNota;
        return (olderNota + newNota) / 2;
    }

    public Disciplina updateDisciplinaNota(Long id, double newNota) throws HttpClientErrorException {
        Disciplina disciplina = getDisciplina(id);
        double finalNota = calcularNota(disciplina.getNota(), newNota);
        disciplina.setNota(finalNota);

        return this.repositorioDeDisciplina.save(disciplina);
    }

    public Disciplina addComentario(Long id, ComentarioDTO comentarioDTO) throws HttpClientErrorException {
        Disciplina disciplina = getDisciplina(id);
        Comentario comentario = new Comentario(comentarioDTO.getComentario());
        disciplina.getComentarios().add(comentario);

        return this.repositorioDeDisciplina.save(disciplina);
    }

}
