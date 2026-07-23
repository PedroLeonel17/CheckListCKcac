package dev.pedro.CodigoKidChecklist.Dto.Aluno;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class AlunosNomesDTO {

    private String nome;

    public AlunosNomesDTO() {
    }

    public AlunosNomesDTO(String nome) {
        this.nome = nome;
    }

}