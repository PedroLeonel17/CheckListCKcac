package dev.pedro.CodigoKidChecklist.Model;

import dev.pedro.CodigoKidChecklist.Enums.HorarioAula;
import dev.pedro.CodigoKidChecklist.Model.Aluno.Aluno;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "checklist")
@Data
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    @Enumerated(EnumType.STRING)
    @Column(name = "horario_aula")
    private HorarioAula horarioAula;
    private boolean compareceu;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    private LocalDate data;

}
