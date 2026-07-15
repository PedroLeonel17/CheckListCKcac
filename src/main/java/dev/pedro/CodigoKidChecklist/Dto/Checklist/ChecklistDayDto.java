package dev.pedro.CodigoKidChecklist.Dto.Checklist;

import dev.pedro.CodigoKidChecklist.Dto.ChecklistDto;
import lombok.Data;

import java.util.List;

@Data
public class ChecklistDayDto {
    public int dia;
    private List<ChecklistDto> checklists;
}
