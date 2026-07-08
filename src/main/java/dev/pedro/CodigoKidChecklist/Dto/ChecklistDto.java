package dev.pedro.CodigoKidChecklist.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class ChecklistDto {

    private String nome;
    private String observacao;
    private String descricao;
    private String horarioAula;
    private boolean presente;
}
