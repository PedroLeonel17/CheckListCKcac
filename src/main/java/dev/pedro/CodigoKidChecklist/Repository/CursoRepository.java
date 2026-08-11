package dev.pedro.CodigoKidChecklist.Repository;

import dev.pedro.CodigoKidChecklist.Model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> deleteByNome(String nome);
    boolean existsByNomeAndIdNot(String nome, Long id);

}