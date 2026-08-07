package dev.pedro.CodigoKidChecklist.Services.Aluno;

import dev.pedro.CodigoKidChecklist.Dto.Aluno.AlunoCadastradoDTO;
import dev.pedro.CodigoKidChecklist.Dto.Aluno.AlunoDTO;
import dev.pedro.CodigoKidChecklist.Dto.Aluno.AlunoInicioCadastro;
import dev.pedro.CodigoKidChecklist.Dto.CursoDto.CursoDto;
import dev.pedro.CodigoKidChecklist.Model.Curso;
import dev.pedro.CodigoKidChecklist.Model.Aluno.Aluno;
import dev.pedro.CodigoKidChecklist.Repository.Aluno.AlunoRepository;
import dev.pedro.CodigoKidChecklist.Services.CursoService.CursoService;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final CursoService cursoService;

    public AlunoService(AlunoRepository alunoRepository, CursoService cursoService) {
        this.alunoRepository = alunoRepository;
        this.cursoService = cursoService;
    }


    public AlunoDTO finalizarCadastro(AlunoCadastradoDTO novoAluno) {
        
        Aluno aluno = new Aluno();
        aluno.setNome(novoAluno.nome());
        aluno.setDataMatricula(novoAluno.dataMatricula());
        aluno.setDataConclusao(novoAluno.dataConclusao());

        List<Curso> cursos = cursoService.listarTodosPeloId(novoAluno.cursos());

        aluno.setCursos(cursos);

        Aluno alunoCadastrado = alunoRepository.save(aluno);
        
        List<CursoDto> cursosDto = cursosDoAluno(alunoCadastrado);

        return new AlunoDTO(alunoCadastrado.getId(), alunoCadastrado.getNome(), cursosDto);
    }

    public AlunoInicioCadastro iniciarCadastro() {
        List<CursoDto> cursos = cursoService.listarCursos();
        return new AlunoInicioCadastro(cursos);
    }

    public AlunoDTO buscarPorId(Long id) {

        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        List<CursoDto> cursosDto = cursosDoAluno(aluno);
        
        AlunoDTO dto = new AlunoDTO(aluno.getId(), aluno.getNome(), cursosDto);

        return dto;
    }

    public void deletarAluno(Long id) {
        alunoRepository.deleteById(id);
    }


    public List<AlunoDTO> listarTodos() {

        return alunoRepository.findAll().stream().map (a -> new AlunoDTO(a.getId(),
                                                        a.getNome(), 
                                                        cursosDoAluno(a))
                                                      )
                                                      .toList(); 

    }

    private List<CursoDto> cursosDoAluno(Aluno a){
        return a.getCursos().stream().map(c -> new CursoDto(c.getId(), c.getNome())).toList();
    }

    
}
