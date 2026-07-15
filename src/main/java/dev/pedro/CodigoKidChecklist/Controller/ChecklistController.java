package dev.pedro.CodigoKidChecklist.Controller;

import dev.pedro.CodigoKidChecklist.Dto.Checklist.ChecklistDateFilterDto;
import dev.pedro.CodigoKidChecklist.Dto.Checklist.ChecklistYearDto;
import dev.pedro.CodigoKidChecklist.Dto.ChecklistDto;
import dev.pedro.CodigoKidChecklist.Dto.ChecklistRespDto;
import dev.pedro.CodigoKidChecklist.Services.ChecklistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    @PostMapping
    public ResponseEntity<ChecklistDto> salvar(@RequestBody ChecklistDto dto) {
        ChecklistDto checklist = checklistService.salvarNovoCheckList(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(checklist);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<ChecklistRespDto>> buscarPorNome(@PathVariable String nome) {

        List<ChecklistRespDto> ocorrencias = checklistService.buscarPorNome(nome);

        return ResponseEntity.ok(ocorrencias);
    }

    @PostMapping("/{filterdate}")
    public ResponseEntity<List<ChecklistYearDto>> buscarPorNome(@RequestBody ChecklistDateFilterDto data) {

        List<ChecklistYearDto> ocorrencias = checklistService.buscarPorData(data);

        return ResponseEntity.ok(ocorrencias);
    }
}
