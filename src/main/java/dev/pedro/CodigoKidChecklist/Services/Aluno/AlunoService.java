package dev.pedro.CodigoKidChecklist.Services.Aluno;

import dev.pedro.CodigoKidChecklist.Dto.Aluno.AlunoCadastroDTO;
import dev.pedro.CodigoKidChecklist.Dto.Aluno.AlunoDTO;
import dev.pedro.CodigoKidChecklist.Model.Aluno.Aluno;
import dev.pedro.CodigoKidChecklist.Repository.Aluno.AlunoRepository;

import org.springframework.stereotype.Service;


@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoDTO salvarNovoAluno(AlunoCadastroDTO dadosEntrada) {


        Aluno alunoEntity = new Aluno();
        alunoEntity.setNome(dadosEntrada.getNome());
        alunoEntity.setCurso(dadosEntrada.getCurso());


        Aluno alunoSalvo = alunoRepository.save(alunoEntity);

        AlunoDTO dadosSaida = new AlunoDTO();


        dadosSaida.setNome(alunoSalvo.getNome());
        dadosSaida.setCurso(alunoSalvo.getCurso());

        return dadosSaida;
    }

    public AlunoDTO buscarPorId(Long id) {

        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        AlunoDTO dto = new AlunoDTO();
        dto.setNome(aluno.getNome());
        dto.setCurso(aluno.getCurso());

        return dto;
    }
}
