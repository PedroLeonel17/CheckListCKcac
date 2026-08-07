package dev.pedro.CodigoKidChecklist.Services;

import dev.pedro.CodigoKidChecklist.Dto.Checklist.ChecklistConsolidadoDto;
import dev.pedro.CodigoKidChecklist.Dto.Checklist.ChecklistCompletoDto;
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
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;


@Service
public class ChecklistService {
    private final ChecklistRepository checklistRepository;
    private final ChecklistServices checklistServices;
    private final ChecklistRules checklistRules;

    private final Queue<ChecklistCompletoDto> filaPendentes = new ArrayDeque<>();

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
                itemBanco.setParecer(itemDto.parecer());
                itemBanco.setObservacao(itemDto.observacao());
                itemBanco.setPresente(itemDto.presente());
                itemBanco.setRecuperacao(itemDto.recuperacao());
                itemBanco.setIntervalo(itemDto.intervalo());
            }
        }

        checklist.setStatus(StatusChecklist.HOMOLOGADO);

        filaPendentes.poll();

        return dadosEntrada;
    }

    public ChecklistCompletoDto criarChecklistPendente(){

        //LocalDate ld = LocalDate.of(2026, 8, 4);
        //LocalTime lt = LocalTime.of(9,15,30);

        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();

        if (!checklistRules.validarPeriodosValidosPreenchimentoChecklist(ld, lt))
            return null;



        String dataAtual = ld.getDayOfWeek().toString();
        HorarioAula horarioAtual = HorarioAula.obterHorarioInicio(lt);

        if(!filaPendentes.isEmpty())

            if(horarioAtual.getValue() == filaPendentes.peek().horaEntrada())
                return filaPendentes.peek();

        Periodo periodo = checklistServices.periodoService().encontrarPeriodo(dataAtual, horarioAtual);

        Checklist checklist = new Checklist();

        checklist.setData(ld);
        checklist.setDiaSemana(dataAtual);
        checklist.setHoraEntrada(periodo.getInicio());
        checklist.setHoraSaida(periodo.getFim());
        checklist.setPeriodo(periodo);
        checklist.setStatus(StatusChecklist.PENDENTE);
        checklist.setProfessores(new ArrayList<>(periodo.getProfessores()));

        for(Aluno aluno : periodo.getAlunos()){
            ItemChecklist itemChecklist = new ItemChecklist();
            itemChecklist.setAluno(aluno);
            itemChecklist.setPresente(true);
            itemChecklist.setIntervalo(true);
            checklist.getItensChecklist().add(itemChecklist);
        }

        checklistRepository.save(checklist);

        List<Professor> professores = checklist.getProfessores();
        List<ItemChecklist> itens = checklist.getItensChecklist();

        List<ProfessorDto> professoresDto = gerarListaDeProfessorDto(professores);
        List<ItemChecklistDto> itensDto = gerarItemChecklistDtos(itens);

        ChecklistCompletoDto pendente = gerarChecklistCompletoDto(checklist, professoresDto, itensDto);

        filaPendentes.add(pendente);

        return pendente;
    }

    public List<ChecklistCompletoDto> findAll(){
        List<Checklist> checklists = checklistRepository.findAll();
        return gerarListaDeChecklistCompletoDto(checklists);
    }

    public List<ChecklistCompletoDto> findConsolidados(){
        List<Checklist> checklists = checklistRepository.findAllByStatus(StatusChecklist.HOMOLOGADO);
        return gerarListaDeChecklistCompletoDto(checklists);
    }

    public List<ChecklistCompletoDto> findPendentes(){
        List<Checklist> checklists = checklistRepository.findAllByStatus(StatusChecklist.PENDENTE);
        return gerarListaDeChecklistCompletoDto(checklists);
    }

    private List<ChecklistCompletoDto> gerarListaDeChecklistCompletoDto(List<Checklist> checklists){
        return checklists.stream().map(check -> {
            List<Professor> professores = check.getProfessores();
            List<ItemChecklist> itens = check.getItensChecklist();
            List<ItemChecklistDto> itensDto = gerarItemChecklistDtos(itens);
            List<ProfessorDto> professoresDto = gerarListaDeProfessorDto(professores);
            return gerarChecklistCompletoDto(check, professoresDto,itensDto);
        }).toList();
    }

    private ChecklistCompletoDto gerarChecklistCompletoDto(Checklist checklist, List<ProfessorDto> professoresDto, List<ItemChecklistDto> itensDto){
        return new ChecklistCompletoDto(checklist.getId(),
                                        checklist.getData(),
                                        checklist.getStatus(),
                                        itensDto,
                                        professoresDto,
                                        checklist.getDiaSemana(),
                                        checklist.getHoraEntrada().getValue(),
                                        checklist.getHoraSaida().getValue());
    }

    private List<ItemChecklistDto> gerarItemChecklistDtos(List<ItemChecklist> itens){
        return itens.stream()
                .map(item -> new ItemChecklistDto(
                        item.getId(),
                        item.getAluno().getNome(),
                        item.getParecer(),
                        item.getObservacao(),
                        item.isPresente(),
                        item.isRecuperacao(),
                        item.isIntervalo()
                ))
                .toList();
    }

    private List<ProfessorDto> gerarListaDeProfessorDto(List<Professor> professores){
        return professores.stream().map(p -> new ProfessorDto(p.getNome())).toList();
    }
}