package dev.pedro.CodigoKidChecklist.Repository.Aluno;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.pedro.CodigoKidChecklist.Model.Aluno.Aluno;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long>  {
    Optional<Aluno> findAlunoByNome(String nome);
}
