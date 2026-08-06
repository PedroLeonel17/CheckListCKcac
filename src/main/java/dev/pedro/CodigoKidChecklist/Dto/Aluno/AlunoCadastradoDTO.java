package dev.pedro.CodigoKidChecklist.Dto.Aluno;

import java.time.LocalDate;
import java.util.List;

import dev.pedro.CodigoKidChecklist.Dto.CursoDto.CursoIdDto;

public record AlunoCadastradoDTO(String nome, 
                               LocalDate dataMatricula, 
                               LocalDate dataConclusao, 
                               List<CursoIdDto> cursos) {
   
}
