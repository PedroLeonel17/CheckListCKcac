package dev.pedro.CodigoKidChecklist.Model;

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

    private String senha;
    // Construtor vazio (obrigatório para o JPA)
    public Aluno() {

    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

}
