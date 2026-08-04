package dev.pedro.CodigoKidChecklist.Repository;

import dev.pedro.CodigoKidChecklist.Model.Checklist;
import dev.pedro.CodigoKidChecklist.Model.ItemChecklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {

}