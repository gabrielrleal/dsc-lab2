package br.ufpb.dsc.DSCLAB2.entidades;
import javax.persistence.*;
import java.util.List;

@Entity @Table(name = "disciplinas")
public class Disciplina {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double nota;
    private int curtidas;

    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    List<Comentario> comentarios;

    public Disciplina() {
    }

    public Disciplina(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

}




