package dev.pedro.CodigoKidChecklist.Services;

import dev.pedro.CodigoKidChecklist.Dto.Aluno.AlunoChecklistDto;
import dev.pedro.CodigoKidChecklist.Dto.Checklist.CheckListMonthDto;
import dev.pedro.CodigoKidChecklist.Dto.Checklist.ChecklistDateFilterDto;
import dev.pedro.CodigoKidChecklist.Dto.Checklist.ChecklistDayDto;
import dev.pedro.CodigoKidChecklist.Dto.Checklist.ChecklistDto;
import dev.pedro.CodigoKidChecklist.Dto.Checklist.ChecklistRespDto;
import dev.pedro.CodigoKidChecklist.Dto.Checklist.ChecklistYearDto;
import dev.pedro.CodigoKidChecklist.Dto.Periodo.PeriodoDto;
import dev.pedro.CodigoKidChecklist.Dto.Professor.ProfessorChecklistDto;
import dev.pedro.CodigoKidChecklist.Enums.HorarioAula;
import dev.pedro.CodigoKidChecklist.Exceptions.AlunoNullException;
import dev.pedro.CodigoKidChecklist.Model.Checklist;
import dev.pedro.CodigoKidChecklist.Model.Periodo;
import dev.pedro.CodigoKidChecklist.Model.Aluno.Aluno;
import dev.pedro.CodigoKidChecklist.Repository.ChecklistRepository;
import dev.pedro.CodigoKidChecklist.Repository.PeriodoRepository;
import dev.pedro.CodigoKidChecklist.Repository.Aluno.AlunoRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChecklistService {
    private final ChecklistRepository checklistRepository;
    private final AlunoRepository alunoRepository;
    private final PeriodoRepository periodoRepository;

    public ChecklistService(ChecklistRepository checklistRepository, AlunoRepository alunoRepository, PeriodoRepository periodoRepository) {
        this.checklistRepository = checklistRepository;
        this.alunoRepository = alunoRepository;
        this.periodoRepository = periodoRepository;
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
        checkDto.setHorarioAula(checklist1.getHorarioAula().toString());
        checkDto.setPresente(checklist1.isCompareceu());
        checkDto.setDescricao(checklist1.getDescricao());
        return checkDto;
    }

    public List<ChecklistDto> buscarTodos() {
        List<Checklist> checklists = checklistRepository.findAll();

        return checklists.stream()
                .map(this::converterParaDto)
                .toList();
    }

    public List<ChecklistRespDto> buscarPorNome(String nome) {

        Aluno aluno = alunoRepository.findAlunoByNome(nome).orElseThrow(() -> new AlunoNullException("Aluno não encontrado"));

        List<Checklist> checklists = checklistRepository.findByAlunoId(aluno.getId()).orElseThrow(() -> new AlunoNullException("Deu ruim não encontrado"));

        return checklists.stream()
                .map(checklist -> {
                    ChecklistRespDto dto = new ChecklistRespDto();
                    dto.setNome(aluno.getNome());
                    dto.setDescricao(checklist.getDescricao());
                    dto.setHorarioAula(checklist.getHorarioAula().toString());
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


    public PeriodoDto buscarDadosPorDataAtual(){
        PeriodoDto periodoDto = new PeriodoDto();
      
       
        Periodo periodo = periodoRepository.findAll().stream().filter(p -> verificarDiaAtual(p.getDiaDaSemana()))
                                                              .filter(p -> verificarHorarioAtual(p.getInicio().getValue(), p.getFim().getValue())).findFirst().orElse(null);


        List<AlunoChecklistDto> alunos = periodo.getAlunos().stream()
                .map(aluno -> {
                    AlunoChecklistDto alunoDto = new AlunoChecklistDto();
                    alunoDto.setNome(aluno.getNome());
                    return alunoDto;
                })
                .toList();

        List<ProfessorChecklistDto> professores = periodo.getProfessores().stream()
                .map(professor -> {
                    ProfessorChecklistDto professorDto = new ProfessorChecklistDto();
                    professorDto.setNome(professor.getNome());
                    return professorDto;
                })
                .toList();

        periodoDto.setInicio(periodo.getInicio().getValue());
        periodoDto.setFim(periodo.getFim().getValue());
        periodoDto.setAlunos(alunos);
        periodoDto.setProfessores(professores);

        return periodoDto;
    }


    private boolean verificarHorarioAtual(String horarioInicio, String horarioFim) {
        LocalTime horaAtual = LocalTime.now();
       
        LocalTime inicio = LocalTime.parse(horarioInicio);
        LocalTime fim = LocalTime.parse(horarioFim);
        System.out.println("Data atual: " + LocalDate.now());
        System.out.println("Hora atual: " + horaAtual);
        System.out.println("Início: " + inicio);
        System.out.println("Fim: " + fim);
        System.out.println("Está dentro do horário? " + (horaAtual.isAfter(inicio) && horaAtual.isBefore(fim)));
        System.out.println("--------------------------------------------------------------------\n");
        return horaAtual.isAfter(inicio) && horaAtual.isBefore(fim);
    }

    private boolean verificarDiaAtual(String diaDaSemana) {
        LocalDate dataAtual = LocalDate.now();
        String diaAtual = dataAtual.getDayOfWeek().toString();
        System.out.println(diaAtual);
        System.out.println("Dia da semana: " + diaDaSemana);
        System.out.println("São iguais? " + diaAtual.equalsIgnoreCase(diaDaSemana));
        System.out.println("--------------------------------------------------------------------\n");
        return diaAtual.equalsIgnoreCase(diaDaSemana);
    }
}
