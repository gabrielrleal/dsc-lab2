package br.ufpb.dsc.DSCLAB2.servicos;
import br.ufpb.dsc.DSCLAB2.DAOs.ComentarioDAO;
import br.ufpb.dsc.DSCLAB2.entidades.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServico {
    private ComentarioDAO repositorioDeComentario;

    @Autowired
    public ComentarioServico(ComentarioDAO comentarioDAO) {
        this.repositorioDeComentario = comentarioDAO;
    }
    public Comentario addComentario(Comentario comentario) {
        return this.repositorioDeComentario.save(comentario);
    }
}