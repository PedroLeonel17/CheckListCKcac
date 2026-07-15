package dev.pedro.CodigoKidChecklist.Repository;

import dev.pedro.CodigoKidChecklist.Dto.Checklist.ChecklistYearDto;
import dev.pedro.CodigoKidChecklist.Model.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {


    Optional<List<Checklist>> findByAlunoId(Long idAluno);

    Optional<List<Checklist>> findByDataBetween(LocalDate inicio, LocalDate fim);
}