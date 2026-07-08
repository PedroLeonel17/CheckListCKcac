package dev.pedro.CodigoKidChecklist.Dto;

import lombok.Data;

@Data
public class ChecklistRespDto {

    private String nome;
    private String observacao;
    private String descricao;
    private String horarioAula;
    private boolean presente;
}
