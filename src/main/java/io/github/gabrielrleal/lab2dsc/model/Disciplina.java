package io.github.gabrielrleal.lab2dsc.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Disciplina {
    @Id @GeneratedValue
    private long id;
    private String nome;
    private double nota;
    private int likes;

    private List<Comentario> comentarios;


    public Disciplina() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Disciplina(String nome) {
        super();
        this.nome = nome;
        // TODO Auto-generated constructor stub

    }

    public Disciplina(String nome, double nota, int likes, List<Comentario> comentarios) {
        super();
        this.nome = nome;
        this.nota = nota;
        this.likes = likes;
        this.comentarios = comentarios;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public long getId() {
        return id;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Disciplina other = (Disciplina) obj;
        if (id != other.id)
            return false;
        return true;
    }




}