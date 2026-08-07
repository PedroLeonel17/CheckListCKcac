package dev.pedro.CodigoKidChecklist.Controller;

import dev.pedro.CodigoKidChecklist.Dto.Checklist.*;
import dev.pedro.CodigoKidChecklist.Services.ChecklistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/checklist") // URL base para tudo relacionado a alunos
@Tag(name = "Checklist", description = "Operações relacionadas ao Checklist")
public class ChecklistController{

    private final ChecklistService checklistService;

    public ChecklistController(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    // Salvar aluno (Mapeia o POST para "/alunos")
    @PatchMapping("/acompanhamento")
    @Operation(summary = "Salva o checklist completo do periodo")
    public ResponseEntity<ChecklistConsolidadoDto> ConsolidarChecklist(@RequestBody ChecklistConsolidadoDto dto) {
        ChecklistConsolidadoDto checklist = checklistService.ConsolidarChecklist(dto);
        return ResponseEntity.status(HttpStatus.OK).body(checklist);
    }

    @GetMapping("/acompanhamento")
    @Operation(summary = "Carrega checklist pendente")
    public ResponseEntity<ChecklistCompletoDto> buscarPorDataAtual() {

        ChecklistCompletoDto ocorrencias = checklistService.criarChecklistPendente();

        return ResponseEntity.ok(ocorrencias);
    }

    @GetMapping
    public ResponseEntity<List<ChecklistCompletoDto>> buscarTodos(){
        return ResponseEntity.ok(checklistService.findAll());
    }
}
