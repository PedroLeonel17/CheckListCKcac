package dev.pedro.CodigoKidChecklist.Services.CursoService;

import java.util.List;
import org.springframework.stereotype.Service;

import dev.pedro.CodigoKidChecklist.Dto.CursoDto.CursoAdicionarDto;
import dev.pedro.CodigoKidChecklist.Dto.CursoDto.CursoDto;
import dev.pedro.CodigoKidChecklist.Dto.CursoDto.CursoIdDto;
import dev.pedro.CodigoKidChecklist.Exceptions.CursoListagemException;
import dev.pedro.CodigoKidChecklist.Model.Curso;
import dev.pedro.CodigoKidChecklist.Repository.CursoRepository;



@Service
public class CursoService {
    private  CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository){
        this.cursoRepository = cursoRepository;
    }

    public CursoDto salvar(CursoAdicionarDto cursoDto){

        Curso curso = new Curso();
        curso.setNome(cursoDto.nome());

        Curso novoCurso = cursoRepository.save(curso);

        CursoDto resp = new CursoDto(novoCurso.getId(), novoCurso.getNome());
        
        return resp;
    }

    public CursoDto deletar(CursoDto cursoDto){

        Curso curso = cursoRepository.deleteByNome(cursoDto.nome()).orElseThrow();

        CursoDto resp = new CursoDto(curso.getId(),curso.getNome());
        
        return resp;
    }

    public CursoDto alterar(CursoDto cursoDto){

        Curso curso = cursoRepository.findById(cursoDto.id())
            .orElseThrow();

        
        if (cursoRepository.existsByNomeAndIdNot(cursoDto.nome(), cursoDto.id())) {
            return null;
        }

        curso.setNome(cursoDto.nome());

        Curso newCurso = cursoRepository.save(curso);
        
        return new CursoDto(newCurso.getId(), newCurso.getNome());
    }


    public List<CursoDto> listarCursos(){

        List<Curso> cursos = cursoRepository.findAll();

        List<CursoDto> cursosDto = cursos.stream().map(c -> new CursoDto(c.getId(),c.getNome())).toList();

        return cursosDto;
    }

    public List<Curso> listarTodosPeloId(List<CursoIdDto> idsDosCursos){

        List<Long> ids = idsDosCursos.stream()
            .map(CursoIdDto::id)
            .toList();

        List<Curso> cursos = cursoRepository.findAllById(ids);

        if(ids.size() != cursos.size())
           throw new CursoListagemException("Um ou mais cursos informados não foram encontrados.");
        
        return cursos;

    }

    public CursoDto encontrar(Long id){
        Curso curso = cursoRepository.findById(id).orElseThrow();
    
        return new CursoDto(curso.getId(), curso.getNome());
    }
}
