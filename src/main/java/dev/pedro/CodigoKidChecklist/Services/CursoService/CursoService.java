package dev.pedro.CodigoKidChecklist.Services.CursoService;

import java.net.URI;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.pedro.CodigoKidChecklist.Dto.CursoDto.CursoDto;
import dev.pedro.CodigoKidChecklist.Model.Curso;
import dev.pedro.CodigoKidChecklist.Repository.CursoRepository;



@Service
public class CursoService {
    private  CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository){
        this.cursoRepository = cursoRepository;
    }

    public CursoDto salvar(CursoDto cursoDto){

        Curso curso = new Curso();
        curso.setNome(cursoDto.nome());

        cursoRepository.save(curso);

        CursoDto resp = new CursoDto(curso.getId(), curso.getNome());
        
        return resp;
    }

    public CursoDto deletar(CursoDto cursoDto){

        Curso curso = cursoRepository.deleteByName(cursoDto.nome()).orElseThrow();

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

    public CursoDto encontrar(Long id){
        Curso curso = cursoRepository.findById(id).orElseThrow();
    
        return new CursoDto(curso.getId(), curso.getNome());
    }
}
