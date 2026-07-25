package dev.pedro.CodigoKidChecklist.Dto.Checklist;



import java.time.LocalDate;

import lombok.Data;

@Data
public class ChecklistDateFilterDto {
    private LocalDate dataInicio;
    private LocalDate dataFim;
}
