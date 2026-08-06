package dev.pedro.CodigoKidChecklist.Model;

import java.util.ArrayList;
import java.util.List;

import dev.pedro.CodigoKidChecklist.Enums.HorarioAula;
import dev.pedro.CodigoKidChecklist.Model.Aluno.Aluno;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "periodos")
@Data
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diaDaSemana;

    @Enumerated(EnumType.STRING)
    @Column(name = "horario_inicio")
    private HorarioAula inicio;

    @Enumerated(EnumType.STRING)
    @Column(name = "horario_fim")
    private HorarioAula fim;

    @ManyToMany
    @JoinTable(
        name = "professor_periodo",
        joinColumns = @JoinColumn(name = "periodo_id"),
        inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> professores = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "aluno_periodo",
        joinColumns = @JoinColumn(name = "periodo_id"),
        inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<Aluno> alunos = new ArrayList<>();


    public Periodo() {
    }
}
