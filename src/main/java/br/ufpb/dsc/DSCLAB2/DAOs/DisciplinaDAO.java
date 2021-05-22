package br.ufpb.dsc.DSCLAB2.DAOs;
import br.ufpb.dsc.DSCLAB2.entidades.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DisciplinaDAO extends JpaRepository<Disciplina, Long> {
    List<Disciplina> findByOrderByNotaDesc();
    List<Disciplina> findByOrderByCurtidasDesc();
}
