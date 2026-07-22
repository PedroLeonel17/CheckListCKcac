package dev.pedro.CodigoKidChecklist.Model.Aluno;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "alunos")
@Data
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String curso;

    public Aluno() {

    }

}
