package io.github.gabrielrleal.lab2dsc.model;

import javax.persistence.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
    @Entity
    public class Comentario {
        @Id
        @GeneratedValue
        private long id;

        private String texto;
        private boolean apagada = false;

        @ManyToOne
        @JoinColumn(name = "id_disciplina")
        private Disciplina disciplina;


        public Comentario() {
            super();
            // TODO Auto-generated constructor stub
        }

        public Comentario(Disciplina disciplina, String texto) {
            super();
            this.disciplina = disciplina;
            this.texto = texto;
            // TODO Auto-generated constructor stub
        }

        public String getTexto() {
            return texto;
        }

        public void setTexto(String texto) {
            this.texto = texto;
        }

        public boolean isApagada() {
            return apagada;
        }

        public void setApagada(boolean apagada) {
            this.apagada = apagada;
        }

    }


