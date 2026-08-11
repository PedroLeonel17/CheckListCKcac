package dev.pedro.CodigoKidChecklist.Controller.Aluno;

import dev.pedro.CodigoKidChecklist.Dto.Aluno.AlunoCadastradoDTO;
import dev.pedro.CodigoKidChecklist.Dto.Aluno.AlunoDTO;
import dev.pedro.CodigoKidChecklist.Model.Aluno.Aluno;
import dev.pedro.CodigoKidChecklist.Services.Aluno.AlunoService;
import dev.pedro.CodigoKidChecklist.Services.CursoService.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/alunos") // URL base para tudo relacionado a alunos
@Tag(name = "Alunos", description = "Operações relacionadas aos cursos")
public class ControllerAluno {

    private final AlunoService alunoService;
    private final CursoService cursoService;

    public ControllerAluno(AlunoService alunoService, CursoService cursoService) {
        this.alunoService = alunoService;
        this.cursoService = cursoService;
    }

    @PostMapping
    @Operation(summary = "Finalizar Cadastro de um aluno")
    public ResponseEntity<AlunoDTO> finalizarCadastro(@RequestBody AlunoCadastradoDTO dto) {
        AlunoDTO novoAluno = alunoService.finalizarCadastro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar um aluno")
    public ResponseEntity<AlunoDTO> buscarPorId(@PathVariable Long id) {

        AlunoDTO aluno = alunoService.buscarPorId(id);

        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um aluno")
    public ResponseEntity<Aluno> deletarAluno(@PathVariable Long id) {
        alunoService.deletarAluno(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nomes")
    @Operation(summary = "Listar todos alunos")
    public ResponseEntity<List<AlunoDTO>> buscarAlunos() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    /*
    @PatchMapping("/cursos/{id}")
    public ResponseEntity<AlunoDTO> atualizarCurso(List<CursoIdDto> cursoIds) {

        AlunoDTO aluno = alunoService.atualizarCurso(cursoIds);
        return ResponseEntity.ok(aluno);
    } */
}
