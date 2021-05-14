package br.ufpb.dsc.DSCLAB2.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.lang.Double;
@Entity
public class Disciplina{
    @Id @GeneratedValue
    private long id;
    private String nome;
    private double notas;
    private double curtida;




    public Disciplina() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Disciplina(String nome) {
        super();
        this.nome = nome;
        // TODO Auto-generated constructor stub

    }

    /*public Disciplina(String nome, List<Double> notas, int curtida, List<Comentario> comentarios) {
        super();
        this.nome = nome;
        this.notas = notas;
        this.curtida = curtida;
        this.comentarios = comentarios;
    }*/

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /*public Double getNotaMedia() {
        double soma = 0;
        double media = 0;
        for(int i=0; i<=notas.size(); i++) {
            soma = soma + i;


        }
        media = soma/notas.size();
        return media;
    }*/
    public Double getNotas(){
        return notas;
    }

    public void setNota() {
        this.notas ++;
    }



    public Double getCurtida() {
        return curtida;
    }

    public void setCurtida() {
        this.curtida++;
    }

    public long getId() {
        return id;
    }

   /* public List<Comentario> getComentarios() {
        return comentarios;
    }*/

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
