package dev.pedro.CodigoKidChecklist.Controller;

import dev.pedro.CodigoKidChecklist.Dto.ChecklistDto;
import dev.pedro.CodigoKidChecklist.Services.ChecklistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checklist") // URL base para tudo relacionado a alunos
public class ChecklistController{

    private final ChecklistService checklistService;

    public ChecklistController(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    // Salvar aluno (Mapeia o POST para "/alunos")
    @PostMapping
    public ResponseEntity<ChecklistDto> salvar(@RequestBody ChecklistDto dto) {
        ChecklistDto checklist = checklistService.salvarNovoCheckList(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(checklist);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ChecklistDto>> buscarPorId(@PathVariable Long id) {

        List<ChecklistDto> ocorrencias = checklistService.buscarPorId(id);

        return ResponseEntity.ok(ocorrencias);
    }
}
