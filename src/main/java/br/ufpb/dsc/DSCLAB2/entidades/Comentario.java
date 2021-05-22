package br.ufpb.dsc.DSCLAB2.entidades;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;


@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comentario;
    @JsonIgnore @ManyToOne @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    public Comentario() {
    }
    public Comentario(String comentario) {

        this.comentario = comentario;
    }
    public Long getId() {

        return id;
    }
    public String getComentario() {

        return comentario;
    }
    public void setComentario(String comentario) {

        this.comentario = comentario;
    }
    public Disciplina getDisciplina() {

        return disciplina;
    }
    public void setDisciplina(Disciplina disciplina) {

        this.disciplina = disciplina;
    }

}