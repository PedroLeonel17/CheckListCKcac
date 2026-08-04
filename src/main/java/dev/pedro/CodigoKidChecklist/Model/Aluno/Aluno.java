package dev.pedro.CodigoKidChecklist.Model.Aluno;

import jakarta.persistence.*;
import java.time.LocalDate;
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

    private LocalDate dataMatricula;
    private LocalDate dataConclusao;

    public Aluno() {

    }

}
