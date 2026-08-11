package dev.pedro.CodigoKidChecklist.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import dev.pedro.CodigoKidChecklist.Model.Aluno.Aluno;

@Entity
@Table(name = "cursos")
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToMany(mappedBy = "cursos")
    private List<Aluno> alunos = new ArrayList<>();

    public Curso() {

    }

}


