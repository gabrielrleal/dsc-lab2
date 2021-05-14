package br.ufpb.dsc.DSCLAB2.dao;

import br.ufpb.dsc.DSCLAB2.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DisciplinaDAO extends JpaRepository<Disciplina, Long> {

}
