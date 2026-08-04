package dev.pedro.CodigoKidChecklist.Services;

import dev.pedro.CodigoKidChecklist.Dto.Checklist.ChecklistConsolidadoDto;
import dev.pedro.CodigoKidChecklist.Dto.Checklist.ChecklistPendenteDto;
import dev.pedro.CodigoKidChecklist.Dto.ItemChecklistDto.ItemChecklistConsolidadoDto;
import dev.pedro.CodigoKidChecklist.Dto.ItemChecklistDto.ItemChecklistDto;
import dev.pedro.CodigoKidChecklist.Dto.Professor.ProfessorDto;
import dev.pedro.CodigoKidChecklist.Enums.HorarioAula;
import dev.pedro.CodigoKidChecklist.Enums.StatusChecklist;
import dev.pedro.CodigoKidChecklist.Model.Aluno.Aluno;
import dev.pedro.CodigoKidChecklist.Model.Checklist;
import dev.pedro.CodigoKidChecklist.Model.ItemChecklist;
import dev.pedro.CodigoKidChecklist.Model.Periodo;
import dev.pedro.CodigoKidChecklist.Model.Professor;
import dev.pedro.CodigoKidChecklist.Repository.ChecklistRepository;
import dev.pedro.CodigoKidChecklist.Services.Checklist.ChecklistServices;
import dev.pedro.CodigoKidChecklist.Services.Rules.ChecklistRules;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChecklistService {
    private final ChecklistRepository checklistRepository;
    private final ChecklistServices checklistServices;
    private final ChecklistRules checklistRules;

    public ChecklistService(ChecklistRepository checklistRepository, ChecklistServices checklistServices, ChecklistRules checklistRules) {
        this.checklistRepository = checklistRepository;
        this.checklistServices = checklistServices;
        this.checklistRules = checklistRules;
    }

    @Transactional
    public ChecklistConsolidadoDto ConsolidarChecklist(ChecklistConsolidadoDto dadosEntrada) {

        Checklist checklist = checklistRepository.getReferenceById(dadosEntrada.id());

        Map<Long, ItemChecklistConsolidadoDto> itensPorId = dadosEntrada.itemConsolidado()
                .stream()
                .collect(Collectors.toMap(
                        ItemChecklistConsolidadoDto::id,
                        item -> item
                ));

        for (ItemChecklist itemBanco : checklist.getItensChecklist()) {

            ItemChecklistConsolidadoDto itemDto = itensPorId.get(itemBanco.getId());

            if (itemDto != null) {
                itemBanco.setCompareceu(itemDto.presente());
                itemBanco.setParecer(itemDto.parecer());
                itemBanco.setObservacao(itemDto.observacao());
            }
        }

        checklist.setStatus(StatusChecklist.HOMOLOGADO);

        return dadosEntrada;
    }

    public ChecklistPendenteDto criarChecklistPendente(){

        LocalDate ld = LocalDate.of(2026, 7, 29);
        LocalTime lt = LocalTime.of(9,15,30);

        if (!checklistRules.validarPeriodosValidosPreenchimentoChecklist(ld, lt))
            return null;

        String dataAtual = ld.getDayOfWeek().toString();
        HorarioAula horarioAtual = HorarioAula.obterHorarioInicio(lt);

        Periodo periodo = checklistServices.periodoService().encontrarPeriodo(dataAtual, horarioAtual);

        Checklist checklist = new Checklist();

        for(Aluno aluno : periodo.getAlunos()){
            ItemChecklist itemChecklist = new ItemChecklist();
            itemChecklist.setAluno(aluno);
            checklist.getItensChecklist().add(itemChecklist);
        }

        checklist.setData(ld);
        checklist.setPeriodo(periodo);
        checklist.setStatus(StatusChecklist.PENDENTE);

        checklistRepository.save(checklist);

        List<Professor> professores = checklist.getPeriodo().getProfessores();
        List<ItemChecklist> itens = checklist.getItensChecklist();



        List<ProfessorDto> professoresDto = professores.stream().map(p -> new ProfessorDto(p.getNome())).toList();
        List<ItemChecklistDto> itensDto = itens.stream()
                .map(item -> new ItemChecklistDto(
                        item.getId(),
                        item.getAluno().getNome(),
                        item.isCompareceu(),
                        item.getObservacao()
                ))
                .toList();

        return new ChecklistPendenteDto(checklist.getId(),
                                        checklist.getData(),
                                        checklist.getStatus(),
                                        itensDto,
                                        professoresDto);
    }
}
