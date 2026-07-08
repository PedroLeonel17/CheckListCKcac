package dev.pedro.CodigoKidChecklist.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "checklist")
@Data
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Date inicio;
    private Date fim;
    private boolean compareceu;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

}
