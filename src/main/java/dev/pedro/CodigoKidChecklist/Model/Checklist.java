package dev.pedro.CodigoKidChecklist.Model;

import dev.pedro.CodigoKidChecklist.Enums.HorarioAula;
import dev.pedro.CodigoKidChecklist.Enums.StatusChecklist;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "checklist")
@Data
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "periodo_id")
    private Periodo periodo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusChecklist status;


    private String diaSemana;

    @Enumerated(EnumType.STRING)
    private HorarioAula horaEntrada;
    @Enumerated(EnumType.STRING)
    private HorarioAula horaSaida;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "checklist_id")
    private List<ItemChecklist> itensChecklist = new ArrayList<>();

}
