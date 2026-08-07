package dev.pedro.CodigoKidChecklist.Dto.Checklist;
import dev.pedro.CodigoKidChecklist.Dto.ItemChecklistDto.ItemChecklistDto;
import dev.pedro.CodigoKidChecklist.Dto.Periodo.PeriodoDto;
import dev.pedro.CodigoKidChecklist.Dto.Professor.ProfessorDto;
import dev.pedro.CodigoKidChecklist.Enums.StatusChecklist;

import java.time.LocalDate;
import java.util.List;

public record ChecklistCompletoDto(Long id,
                                   LocalDate data,
                                   StatusChecklist status,
                                   List<ItemChecklistDto> itemChecklistDto,
                                   List<ProfessorDto> professores,
                                   String diaSemana,
                                   String horaEntrada,
                                   String horaSaida) {
}
