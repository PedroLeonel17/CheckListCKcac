package dev.pedro.CodigoKidChecklist.Services;

import dev.pedro.CodigoKidChecklist.Dto.Checklist.CheckListMonthDto;
import dev.pedro.CodigoKidChecklist.Dto.Checklist.ChecklistDateFilterDto;
import dev.pedro.CodigoKidChecklist.Dto.Checklist.ChecklistDayDto;
import dev.pedro.CodigoKidChecklist.Dto.Checklist.ChecklistYearDto;
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
import java.util.Map;
import java.util.stream.Collectors;

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
        checklist.setData(dadosEntrada.getData());
        Checklist checklist1 = checklistRepository.save(checklist);

        ChecklistDto checkDto = new ChecklistDto();
        checkDto.setHorarioAula(checklist1.getHorarioAula().getValue());
        checkDto.setPresente(checklist1.isCompareceu());
        checkDto.setDescricao(checklist1.getDescricao());
        return checkDto;
    }

    public List<ChecklistRespDto> buscarPorNome(String nome) {

        Aluno aluno = alunoRepository.findAlunoByNome(nome).orElseThrow(() -> new AlunoNullException("Aluno não encontrado"));

        List<Checklist> checklists = checklistRepository.findByAlunoId(aluno.getId()).orElseThrow(() -> new AlunoNullException("Deu ruim não encontrado"));

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

    public List<ChecklistYearDto> buscarPorData(ChecklistDateFilterDto dto) {

        List<Checklist> checklists = checklistRepository
                .findByDataBetween(dto.getDataInicio(), dto.getDataFim()).orElseThrow();

        Map<Integer, Map<Integer, Map<Integer, List<Checklist>>>> agrupado =
                checklists.stream()
                        .collect(Collectors.groupingBy(
                                c -> c.getData().getYear(),
                                Collectors.groupingBy(
                                        c -> c.getData().getMonthValue(),
                                        Collectors.groupingBy(
                                                c -> c.getData().getDayOfMonth()
                                        )
                                )
                        ));


        return agrupado.entrySet()
                .stream()
                .map(anoEntry -> {

                    ChecklistYearDto yearDto = new ChecklistYearDto();
                    yearDto.setAno(anoEntry.getKey());


                    List<CheckListMonthDto> meses = anoEntry.getValue()
                            .entrySet()
                            .stream()
                            .map(mesEntry -> {

                                CheckListMonthDto monthDto = new CheckListMonthDto();
                                monthDto.setMes(mesEntry.getKey());


                                List<ChecklistDayDto> dias = mesEntry.getValue()
                                        .entrySet()
                                        .stream()
                                        .map(diaEntry -> {

                                            ChecklistDayDto dayDto = new ChecklistDayDto();

                                            dayDto.setDia(diaEntry.getKey());

                                            List<ChecklistDto> lista = diaEntry.getValue()
                                                    .stream()
                                                    .map(this::converterParaDto)
                                                    .toList();

                                            dayDto.setChecklists(lista);

                                            return dayDto;

                                        })
                                        .toList();


                                monthDto.setDias(dias);

                                return monthDto;

                            })
                            .toList();


                    yearDto.setMeses(meses);

                    return yearDto;

                })
                .toList();
    }


    private ChecklistDto converterParaDto(Checklist checklist) {

        ChecklistDto dto = new ChecklistDto();

        dto.setNome(checklist.getAluno().getNome());
        dto.setObservacao(checklist.getDescricao());
        dto.setDescricao(checklist.getDescricao());
        dto.setData(checklist.getData());
        dto.setPresente(checklist.isCompareceu());

        return dto;
    }
}
