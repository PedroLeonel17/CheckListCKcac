package dev.pedro.CodigoKidChecklist.Dto.Checklist;

import dev.pedro.CodigoKidChecklist.Dto.ItemChecklistDto.ItemChecklistConsolidadoDto;

import java.util.List;

public record ChecklistConsolidadoDto(Long id, List<ItemChecklistConsolidadoDto> itemConsolidado) {
}
