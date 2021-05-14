package br.ufpb.dsc.DSCLAB2.dto;

public class DisciplinaDTO {
    /*private String nome;

    public DisciplinaDTO(String nome) {
        super();
        this.nome = nome;
        // TODO Auto-generated constructor stub
    }

    public String getNome() {
        return nome;
    }*/

    private double curtida;

    public DisciplinaDTO(double curtida) {
        super();
        this.curtida = curtida;
        // TODO Auto-generated constructor stub
    }
    public void setCurtida(){
        this.curtida++;
    }
    public double getCurtida() {
        return curtida;
    }






}