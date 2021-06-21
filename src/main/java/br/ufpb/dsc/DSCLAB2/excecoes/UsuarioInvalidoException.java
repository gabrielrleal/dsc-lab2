package br.ufpb.dsc.DSCLAB2.excecoes;

public class UsuarioInvalidoException extends IllegalArgumentException {

    public UsuarioInvalidoException(String s) {
        super(s);
    }

    public UsuarioInvalidoException() {
        super();
    }
}