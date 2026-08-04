package dev.pedro.CodigoKidChecklist.Dto.Periodo;


import dev.pedro.CodigoKidChecklist.Dto.Aluno.AlunoDTO;
import dev.pedro.CodigoKidChecklist.Dto.Professor.ProfessorDto;

import java.util.List;

public record PeriodoDto(String inicio,
                         String fim,
                         List<ProfessorDto> professores,
                         List<AlunoDTO> alunos) {}
