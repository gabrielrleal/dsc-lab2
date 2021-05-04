package io.github.gabrielrleal.lab2dsc.dao;
import io.github.gabrielrleal.lab2dsc.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DisciplinaDAO extends JpaRepository<Disciplina, Long> {

}
