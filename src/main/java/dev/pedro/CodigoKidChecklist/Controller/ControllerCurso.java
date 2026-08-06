package dev.pedro.CodigoKidChecklist.Controller;

import dev.pedro.CodigoKidChecklist.Dto.CursoDto.CursoAdicionarDto;
import dev.pedro.CodigoKidChecklist.Dto.CursoDto.CursoDto;
import dev.pedro.CodigoKidChecklist.Services.CursoService.CursoService;
import jakarta.websocket.server.PathParam;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/curso") // URL base para tudo relacionado a alunos
public class ControllerCurso {

    private final CursoService cursoService;

    public ControllerCurso(CursoService cursoService) {
        this.cursoService = cursoService;
    }
    
    @PostMapping
    public ResponseEntity<CursoDto> salvar(@RequestBody CursoAdicionarDto dto) {
        CursoDto cursoDto = cursoService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDto);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<CursoDto> verificar(@PathVariable Long id) {
        CursoDto cursoDto = cursoService.encontrar(id);
        return ResponseEntity.status(HttpStatus.OK).body(cursoDto);
    }

    // Salvar aluno (Mapeia o POST para "/alunos")
    @PutMapping
    public ResponseEntity<CursoDto> alterar(@RequestBody CursoDto dto) {
        CursoDto cursoDto = cursoService.alterar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDto);
    }

    // Salvar aluno (Mapeia o POST para "/alunos")
    @DeleteMapping
    public ResponseEntity<CursoDto> deletar(@RequestBody CursoDto dto) {
        CursoDto cursoDto = cursoService.deletar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDto);
    }

    @GetMapping
    public ResponseEntity<List<CursoDto>> listarTodos() {
        List<CursoDto> cursosDto = cursoService.listarCursos();
        return ResponseEntity.status(HttpStatus.CREATED).body(cursosDto);
    }



}
