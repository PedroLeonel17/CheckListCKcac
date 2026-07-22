package dev.pedro.CodigoKidChecklist.Dto.Checklist;

import lombok.Data;

@Data
public class ChecklistRespDto {

    private String nome;
    private String observacao;
    private String descricao;
    private String horarioAula;
    private boolean presente;
}
