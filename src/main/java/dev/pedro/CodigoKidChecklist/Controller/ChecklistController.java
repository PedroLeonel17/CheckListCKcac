package dev.pedro.CodigoKidChecklist.Controller;

import dev.pedro.CodigoKidChecklist.Dto.Checklist.*;
import dev.pedro.CodigoKidChecklist.Dto.Periodo.PeriodoDto;
import dev.pedro.CodigoKidChecklist.Services.ChecklistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/checklist") // URL base para tudo relacionado a alunos
public class ChecklistController{

    private final ChecklistService checklistService;

    public ChecklistController(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    // Salvar aluno (Mapeia o POST para "/alunos")
    @PatchMapping("/acompanhamento")
    public ResponseEntity<ChecklistConsolidadoDto> ConsolidarChecklist(@RequestBody ChecklistConsolidadoDto dto) {
        ChecklistConsolidadoDto checklist = checklistService.ConsolidarChecklist(dto);
        return ResponseEntity.status(HttpStatus.OK).body(checklist);
    }

    @GetMapping("/acompanhamento")
    public ResponseEntity<ChecklistPendenteDto> buscarPorDataAtual() {

        ChecklistPendenteDto ocorrencias = checklistService.criarChecklistPendente();

        return ResponseEntity.ok(ocorrencias);
    }
}
