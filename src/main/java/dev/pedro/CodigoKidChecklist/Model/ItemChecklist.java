package dev.pedro.CodigoKidChecklist.Model;

import dev.pedro.CodigoKidChecklist.Model.Aluno.Aluno;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "item_checklist")
@Data
public class ItemChecklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String parecer;
    private String observacao;
    private boolean presente;
    private boolean intervalo;
    private boolean recuperacao;


    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    public ItemChecklist() {
    }
}
