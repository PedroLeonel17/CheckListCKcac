package dev.pedro.CodigoKidChecklist.Controller;

import dev.pedro.CodigoKidChecklist.Dto.AlunoCadastroDTO;
import dev.pedro.CodigoKidChecklist.Dto.AlunoDTO;
import dev.pedro.CodigoKidChecklist.Services.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos") // URL base para tudo relacionado a alunos
public class ControllerAluno {

    private final AlunoService alunoService;

    public ControllerAluno(AlunoService alunoService) {
        this.alunoService = alunoService;
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
}
