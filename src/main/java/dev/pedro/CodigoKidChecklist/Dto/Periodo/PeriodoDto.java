package dev.pedro.CodigoKidChecklist.Dto.Periodo;

import lombok.Data;

import java.util.List;

import dev.pedro.CodigoKidChecklist.Dto.Aluno.AlunoChecklistDto;
import dev.pedro.CodigoKidChecklist.Dto.Professor.ProfessorChecklistDto;


@Data
public class PeriodoDto {

    private String inicio;
    private String fim;
    private List<ProfessorChecklistDto> professores;
    private List<AlunoChecklistDto> alunos;
    
    public PeriodoDto() {

    }
}
