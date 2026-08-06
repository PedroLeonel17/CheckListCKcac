package dev.pedro.CodigoKidChecklist.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.pedro.CodigoKidChecklist.Enums.HorarioAula;
import dev.pedro.CodigoKidChecklist.Model.Periodo;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {
    Optional<Periodo> findByDiaDaSemanaAndInicio(String dia, HorarioAula inicio);
}
