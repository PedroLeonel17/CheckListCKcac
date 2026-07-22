package dev.pedro.CodigoKidChecklist.Dto.Checklist;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ChecklistDto {

    private String nome;
    private String observacao;
    private String descricao;
    private String horarioAula;
    private LocalDate data;
    private boolean presente;
}
