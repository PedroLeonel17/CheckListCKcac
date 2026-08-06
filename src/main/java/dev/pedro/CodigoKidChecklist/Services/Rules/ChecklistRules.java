package dev.pedro.CodigoKidChecklist.Services.Rules;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class ChecklistRules {

    public boolean validarPeriodosValidosPreenchimentoChecklist(LocalDate dataAtual, LocalTime horaAtual){

        if(!RegrasPeriodo.verificarDiasValidos(DayOfWeek.TUESDAY,
                                               DayOfWeek.SATURDAY,
                                               dataAtual)){
            return false;
        }

        boolean horarioManha = RegrasPeriodo.verificarHoraValida(LocalTime.of(8, 0),
                                                                 LocalTime.of(12, 0),
                                                                 horaAtual);

        boolean horarioTarde = RegrasPeriodo.verificarHoraValida(LocalTime.of(13, 30),
                                                                 LocalTime.of(17, 30),
                                                                 horaAtual);
        
        if(!horarioManha && !horarioTarde){
            return false;
        }

        if(dataAtual.getDayOfWeek() == DayOfWeek.SATURDAY && !horarioManha){
            return false;
        }

        return true;
    }
}
