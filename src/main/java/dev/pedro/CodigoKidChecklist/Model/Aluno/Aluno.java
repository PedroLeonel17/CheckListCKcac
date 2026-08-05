package dev.pedro.CodigoKidChecklist.Model.Aluno;


import dev.pedro.CodigoKidChecklist.Model.Curso;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;
import java.util.List;
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

    @ManyToMany
    @JoinTable(
        name = "aluno_curso",
        joinColumns = @JoinColumn(name = "aluno_id"),
        inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private List<Curso> cursos;

    public Aluno() {

    }

}
