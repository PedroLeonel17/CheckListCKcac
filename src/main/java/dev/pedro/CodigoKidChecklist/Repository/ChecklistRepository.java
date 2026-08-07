package dev.pedro.CodigoKidChecklist.Repository;

import dev.pedro.CodigoKidChecklist.Enums.StatusChecklist;
import dev.pedro.CodigoKidChecklist.Model.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {
    List<Checklist> findAllByStatus(StatusChecklist status);
}