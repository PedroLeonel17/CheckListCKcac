package dev.pedro.CodigoKidChecklist.Services;

import dev.pedro.CodigoKidChecklist.Dto.ChecklistDto;
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

        Aluno aluno = alunoRepository.findById(dadosEntrada.getAlunoId())
                       .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Checklist checklist = new Checklist();

        checklist.setAluno(aluno);
        checklist.setInicio(dadosEntrada.getInicio());
        checklist.setFim(dadosEntrada.getFim());
        checklist.setCompareceu(dadosEntrada.isCompareceu());
        checklist.setDescricao(dadosEntrada.getDescricao());

        Checklist checklist1 = checklistRepository.save(checklist);

        ChecklistDto checkDto = new ChecklistDto();
        checkDto.setAlunoId(aluno.getId());
        checkDto.setInicio(checklist1.getInicio());
        checkDto.setFim(checklist1.getFim());
        checkDto.setCompareceu(checklist1.isCompareceu());
        checkDto.setDescricao(checklist1.getDescricao());
        return checkDto;
    }

    public List<ChecklistDto> buscarPorId(Long id) {
        List<Checklist> checklists = checklistRepository.findByAlunoId(id);

        return checklists.stream()
                .map(checklist -> {
                    ChecklistDto dto = new ChecklistDto();
                    dto.setDescricao(checklist.getDescricao());
                    dto.setInicio(checklist.getInicio());
                    dto.setFim(checklist.getFim());
                    dto.setCompareceu(checklist.isCompareceu());
                    dto.setAlunoId(checklist.getAluno().getId());
                    return dto;
                })
                .toList();
    }
}
