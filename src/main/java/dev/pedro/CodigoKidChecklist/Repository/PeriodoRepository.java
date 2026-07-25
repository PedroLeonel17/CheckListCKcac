package dev.pedro.CodigoKidChecklist.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.pedro.CodigoKidChecklist.Model.Periodo;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {
    
}
