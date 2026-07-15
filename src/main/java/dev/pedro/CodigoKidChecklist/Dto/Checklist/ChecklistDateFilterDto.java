package dev.pedro.CodigoKidChecklist.Dto.Checklist;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ChecklistDateFilterDto {
    private LocalDate dataInicio;
    private LocalDate dataFim;
}
