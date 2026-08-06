package dev.pedro.CodigoKidChecklist.Dto.Aluno;

import java.util.List;

import dev.pedro.CodigoKidChecklist.Dto.CursoDto.CursoDto;

public record AlunoInicioCadastro(List<CursoDto> cursos) {
    
}
