package dev.pedro.CodigoKidChecklist.Repository;

import dev.pedro.CodigoKidChecklist.Model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long>  {
    Optional<Aluno> findAlunoByNome(String nome);
}
