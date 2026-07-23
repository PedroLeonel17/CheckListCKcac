package dev.pedro.CodigoKidChecklist.Services.Aluno;

import dev.pedro.CodigoKidChecklist.Dto.Aluno.AlunoCadastroDTO;
import dev.pedro.CodigoKidChecklist.Dto.Aluno.AlunoDTO;
import dev.pedro.CodigoKidChecklist.Dto.Aluno.AlunosNomesDTO;
import dev.pedro.CodigoKidChecklist.Model.Aluno.Aluno;
import dev.pedro.CodigoKidChecklist.Repository.Aluno.AlunoRepository;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoDTO salvarNovoAluno(AlunoCadastroDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setCurso(dto.getCurso());

        Aluno alunoSalvo = alunoRepository.save(aluno);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setNome(alunoSalvo.getNome());
        alunoDTO.setCurso(alunoSalvo.getCurso());

        return alunoDTO;
    }

    public AlunoDTO buscarPorId(Long id) {

        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        AlunoDTO dto = new AlunoDTO();
        dto.setNome(aluno.getNome());
        dto.setCurso(aluno.getCurso());

        return dto;
    }

    public Aluno deletarAluno(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        alunoRepository.delete(aluno);

        Aluno dto = new Aluno();
        dto.setNome(aluno.getNome());
        dto.setCurso(aluno.getCurso());

        return dto;
    }

    public List<AlunosNomesDTO> buscarTodosNomesAlunos() {
        return alunoRepository.findAll()
                .stream()
                .map(aluno -> new AlunosNomesDTO(aluno.getNome()))
                .toList();
    }
}
