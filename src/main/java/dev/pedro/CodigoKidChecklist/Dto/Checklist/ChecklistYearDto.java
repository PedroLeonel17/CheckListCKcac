package dev.pedro.CodigoKidChecklist.Dto.Checklist;
import lombok.Data;

import java.util.List;

@Data
public class ChecklistYearDto {
    private int ano;
    private List<CheckListMonthDto> meses;
}
