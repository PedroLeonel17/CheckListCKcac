package dev.pedro.CodigoKidChecklist.Controller.Aluno;

import dev.pedro.CodigoKidChecklist.Dto.Aluno.AlunoCadastroDTO;
import dev.pedro.CodigoKidChecklist.Dto.Aluno.AlunoDTO;
import dev.pedro.CodigoKidChecklist.Dto.Checklist.ChecklistDto;

import dev.pedro.CodigoKidChecklist.Services.ChecklistService;
import dev.pedro.CodigoKidChecklist.Services.Aluno.AlunoService;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/alunos") // URL base para tudo relacionado a alunos
public class ControllerAluno {

    private final AlunoService alunoService;
    private final ChecklistService checklistService;

    public ControllerAluno(AlunoService alunoService, ChecklistService checklistService) {
        this.alunoService = alunoService;
        this.checklistService = checklistService;
    }

    // Salvar aluno (Mapeia o POST para "/alunos")
    @PostMapping
    public ResponseEntity<AlunoDTO> salvar(@RequestBody AlunoCadastroDTO dto) {
        AlunoDTO novoAluno = alunoService.salvarNovoAluno(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> buscarPorId(@PathVariable Long id) {

        AlunoDTO aluno = alunoService.buscarPorId(id);

        return ResponseEntity.ok(aluno);
    }

    @GetMapping("/alunos")
    public List<ChecklistDto> listarAlunos() {
        return checklistService.buscarTodos();
    }
}
