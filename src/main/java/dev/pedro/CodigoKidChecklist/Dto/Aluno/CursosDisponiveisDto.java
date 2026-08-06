package dev.pedro.CodigoKidChecklist.Dto.Aluno;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CursosDisponiveisDto {
    
    private String curso;

    public CursosDisponiveisDto() {
    }

    public CursosDisponiveisDto(String curso) {
        this.curso = curso;
    }
}
