package dev.pedro.CodigoKidChecklist.Dto.Checklist;

import lombok.Data;

import java.util.List;

@Data
public class CheckListMonthDto {
    List<ChecklistDayDto> dias;
    public int mes;
}
