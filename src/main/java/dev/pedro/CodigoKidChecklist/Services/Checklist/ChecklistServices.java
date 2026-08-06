package dev.pedro.CodigoKidChecklist.Services.Checklist;

import dev.pedro.CodigoKidChecklist.Services.Aluno.AlunoService;
import dev.pedro.CodigoKidChecklist.Services.Periodo.PeriodoService;
import org.springframework.stereotype.Service;

@Service
public record ChecklistServices(PeriodoService periodoService, AlunoService alunoService) {

}
