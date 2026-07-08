package dev.pedro.CodigoKidChecklist.Services;

import dev.pedro.CodigoKidChecklist.Dto.ChecklistDto;
import dev.pedro.CodigoKidChecklist.Dto.ChecklistRespDto;
import dev.pedro.CodigoKidChecklist.Enums.HorarioAula;
import dev.pedro.CodigoKidChecklist.Exceptions.AlunoNullException;
import dev.pedro.CodigoKidChecklist.Model.Aluno;
import dev.pedro.CodigoKidChecklist.Model.Checklist;
import dev.pedro.CodigoKidChecklist.Repository.AlunoRepository;
import dev.pedro.CodigoKidChecklist.Repository.ChecklistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChecklistService {
    private final ChecklistRepository checklistRepository;
    private final AlunoRepository alunoRepository;

    public ChecklistService(ChecklistRepository checklistRepository, AlunoRepository alunoRepository) {
        this.checklistRepository = checklistRepository;
        this.alunoRepository = alunoRepository;
    }

    public ChecklistDto salvarNovoCheckList(ChecklistDto dadosEntrada) {

        Aluno aluno = alunoRepository.findAlunoByNome(dadosEntrada.getNome())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        System.out.println(aluno);
        Checklist checklist = new Checklist();

        checklist.setAluno(aluno);
        checklist.setHorarioAula(HorarioAula.valueOf(dadosEntrada.getHorarioAula()));
        checklist.setCompareceu(dadosEntrada.isPresente());
        checklist.setDescricao(dadosEntrada.getDescricao());

        Checklist checklist1 = checklistRepository.save(checklist);

        ChecklistDto checkDto = new ChecklistDto();
        checkDto.setHorarioAula(checklist1.getHorarioAula().getValue());
        checkDto.setPresente(checklist1.isCompareceu());
        checkDto.setDescricao(checklist1.getDescricao());
        return checkDto;
    }

    public List<ChecklistRespDto> buscarPorNome(String nome) {

        Aluno aluno = alunoRepository.findAlunoByNome(nome).orElseThrow(() -> new AlunoNullException("Aluno não encontrado"));

        List<Checklist> checklists = checklistRepository.findByAlunoId(aluno.getId());

        return checklists.stream()
                .map(checklist -> {
                    ChecklistRespDto dto = new ChecklistRespDto();
                    dto.setNome(aluno.getNome());
                    dto.setDescricao(checklist.getDescricao());
                    dto.setHorarioAula(checklist.getHorarioAula().getValue());
                    dto.setPresente(checklist.isCompareceu());
                    return dto;
                })
                .toList();
    }
}
