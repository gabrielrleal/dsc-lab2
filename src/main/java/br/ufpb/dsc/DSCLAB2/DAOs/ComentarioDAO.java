package br.ufpb.dsc.DSCLAB2.DAOs;
import br.ufpb.dsc.DSCLAB2.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioDAO extends JpaRepository<Comentario, Long> {
}
