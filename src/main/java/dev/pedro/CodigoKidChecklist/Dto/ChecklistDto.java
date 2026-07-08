package dev.pedro.CodigoKidChecklist.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class ChecklistDto {

    private String descricao;
    private Date inicio;
    private Date fim;
    private boolean compareceu;

    private Long alunoId;
}
