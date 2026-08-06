package dev.pedro.CodigoKidChecklist.Services.Periodo;

import dev.pedro.CodigoKidChecklist.Enums.HorarioAula;
import dev.pedro.CodigoKidChecklist.Model.Periodo;
import dev.pedro.CodigoKidChecklist.Repository.PeriodoRepository;
import org.springframework.stereotype.Service;

@Service
public class PeriodoService {

    private final PeriodoRepository periodoRepository;

    public PeriodoService(PeriodoRepository periodoRepository){
        this.periodoRepository = periodoRepository;
    }

    public Periodo encontrarPeriodo(String dia, HorarioAula inicio ){
        return periodoRepository.findByDiaDaSemanaAndInicio(dia, inicio).orElseThrow();
    }
}
