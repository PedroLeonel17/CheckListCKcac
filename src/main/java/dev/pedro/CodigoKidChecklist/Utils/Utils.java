package dev.pedro.CodigoKidChecklist.Utils;

import java.time.LocalDate;
import java.util.List;

import dev.pedro.CodigoKidChecklist.Dto.Aluno.AlunoChecklistDto;
import dev.pedro.CodigoKidChecklist.Dto.Periodo.PeriodoDto;
import dev.pedro.CodigoKidChecklist.Dto.Professor.ProfessorChecklistDto;
import dev.pedro.CodigoKidChecklist.Model.Periodo;

public final class Utils {
    
    public static PeriodoDto CreatePeriodoDto(Periodo periodo, List<AlunoChecklistDto> alunos, List<ProfessorChecklistDto> professores){
        PeriodoDto p = new PeriodoDto();
        p.setInicio(periodo.getInicio().getValue());
        p.setFim(periodo.getFim().getValue());
        p.setAlunos(alunos);
        p.setProfessores(professores);
        p.setData(LocalDate.now());
        return p;
    }

}
