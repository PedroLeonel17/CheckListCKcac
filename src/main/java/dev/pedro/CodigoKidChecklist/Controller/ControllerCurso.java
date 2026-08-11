package dev.pedro.CodigoKidChecklist.Controller;

import dev.pedro.CodigoKidChecklist.Dto.CursoDto.CursoAdicionarDto;
import dev.pedro.CodigoKidChecklist.Dto.CursoDto.CursoDto;
import dev.pedro.CodigoKidChecklist.Services.CursoService.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/curso") // URL base para tudo relacionado a alunos
@Tag(name = "Curso", description = "Operações relacionadas ao Curso")
public class ControllerCurso {

    private final CursoService cursoService;

    public ControllerCurso(CursoService cursoService) {
        this.cursoService = cursoService;
    }
    
    @PostMapping
    @Operation(summary = "Adiciona um novo curso")
    public ResponseEntity<CursoDto> salvar(@RequestBody CursoAdicionarDto dto) {
        CursoDto cursoDto = cursoService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDto);
    }

    
    @GetMapping("/{id}")
    @Operation(summary = "Pegar infos de um Curso")
    public ResponseEntity<CursoDto> verificar(@PathVariable Long id) {
        CursoDto cursoDto = cursoService.encontrar(id);
        return ResponseEntity.status(HttpStatus.OK).body(cursoDto);
    }

    @PutMapping
    @Operation(summary = "Alterar dados de um curso")
    public ResponseEntity<CursoDto> alterar(@RequestBody CursoDto dto) {
        CursoDto cursoDto = cursoService.alterar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDto);
    }


    @DeleteMapping
    @Operation(summary = "Delete um curso")
    public ResponseEntity<CursoDto> deletar(@RequestBody CursoDto dto) {
        CursoDto cursoDto = cursoService.deletar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDto);
    }

    @GetMapping
    @Operation(summary = "Lista todos cursos disponiveis")
    public ResponseEntity<List<CursoDto>> listarTodos() {
        List<CursoDto> cursosDto = cursoService.listarCursos();
        return ResponseEntity.status(HttpStatus.CREATED).body(cursosDto);
    }



}
