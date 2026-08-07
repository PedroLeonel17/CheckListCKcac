package dev.pedro.CodigoKidChecklist.Model;

import java.util.ArrayList;
import java.util.List;

import dev.pedro.CodigoKidChecklist.Model.Aluno.Aluno;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "professores")
@Data
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany(mappedBy = "professores")
    private List<Checklist> checklists = new ArrayList<>();
    public Professor() {
    }
}
